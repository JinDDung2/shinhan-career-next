package minipjt01.board;

import minipjt01.board.member.Member;
import minipjt01.board.member.MemberRepository;
import minipjt01.board.member.MemberRequestDto;
import minipjt01.board.member.MemberService;
import minipjt01.board.post.Post;
import minipjt01.board.post.PostRepository;
import minipjt01.board.post.PostRequestDto;
import minipjt01.board.post.PostService;

import java.util.List;
import java.util.Scanner;

public class BoardCLI {

    private static final MemberRepository memberRepository = new MemberRepository();
    private static final PostRepository postRepository = new PostRepository();
    private static final MemberService memberService = new MemberService(memberRepository);
    private static final PostService postService = new PostService(memberRepository, postRepository);

    public static void main(String[] args) {
        String initMessage = """
                <  게시판메뉴   >
                1. 전체 글 보기
                2. 회원 등록
                3. 글 등록
                4. 글 삭제
                5. 회원 전체 보기
                6. 회원번호로 회원 찾기
                7. 이메일로 회원 찾기
                8. 글번호로 글 찾기
                9. 제목으로 글 검색
                10. 내용으로 글 검색
                0. 종료
                """;

        memberService.loadToFile();
        postService.loadToFile();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println(initMessage);
            System.out.print("메뉴 선택: ");

            int menu = readInt(sc);

            switch (menu) {
                case 1 -> printPosts();
                case 2 -> createMember(sc);
                case 3 -> createPost(sc);
                case 4 -> deletePost(sc);
                case 5 -> printMembers();
                case 6 -> findMemberById(sc);
                case 7 -> findMemberByEmail(sc);
                case 8 -> findPostById(sc);
                case 9 -> searchPostsByTitle(sc);
                case 10 -> searchPostsByContent(sc);
                case 0 -> {
                    memberService.saveToFile();
                    postService.saveToFile();
                    System.out.println("프로그램을 종료합니다");
                    return;
                }
                default -> System.out.println("잘못된 메뉴입니다.");
            }

        }
    }

    private static void printPosts() {
        List<Post> posts = postService.getPost();
        printPostList(posts);
    }

    private static void printPostList(List<Post> posts) {
        if (posts.isEmpty()) {
            System.out.println("등록된 글이 없습니다.");
            return;
        }

        for (Post post : posts) {
            Member writer = post.getWriter();
            System.out.printf("[%d] %s - %s (%s)%n",
                    post.getId(),
                    post.getTitle(),
                    writer.getNickname(),
                    post.getCreatedAt());
        }
    }

    private static void printPost(Post post) {
        if (post == null) {
            System.out.println("글을 찾을 수 없습니다.");
            return;
        }

        Member writer = post.getWriter();
        System.out.printf("[%d] %s - %s (%s)%n내용: %s%n",
                post.getId(),
                post.getTitle(),
                writer.getNickname(),
                post.getCreatedAt(),
                post.getContent());
    }

    private static void createMember(Scanner sc) {
        System.out.print("이메일: ");
        String email = sc.nextLine();
        System.out.print("비밀번호: ");
        String password = sc.nextLine();
        System.out.print("닉네임: ");
        String nickname = sc.nextLine();

        try {
            MemberRequestDto request = new MemberRequestDto(email, password, nickname);
            Long memberId = memberService.createMember(request);
            if (memberId == null) {
                System.out.println("회원 등록 실패");
                return;
            }

            System.out.println("회원 등록 완료! 회원번호: " + memberId);
        } catch (IllegalArgumentException e) {
            System.out.println("회원 등록 실패: " + e.getMessage());
            return;
        }
    }

    private static void createPost(Scanner sc) {
        System.out.print("작성자 회원번호: ");
        Long memberId = readLong(sc);
        System.out.print("제목: ");
        String title = sc.nextLine();
        System.out.print("내용: ");
        String content = sc.nextLine();

        PostRequestDto request = new PostRequestDto(title, content);
        Long postId = postService.createPost(memberId, request);
        if (postId == null) {
            System.out.println("글 등록 실패");
            return;
        }

        System.out.println("글 등록 완료! 글번호: " + postId);
    }

    private static void deletePost(Scanner sc) {
        System.out.print("삭제할 글번호: ");
        Long postId = readLong(sc);
        if (postService.deletePost(postId)) {
            System.out.println("삭제 완료");
        } else {
            System.out.println("삭제 실패");
        }
    }

    private static void printMembers() {
        List<Member> members = memberService.getMember();
        if (members.isEmpty()) {
            System.out.println("등록된 회원이 없습니다.");
            return;
        }

        for (Member member : members) {
            printMember(member);
        }
    }

    private static void findMemberById(Scanner sc) {
        System.out.print("회원번호: ");
        Member member = memberService.getMember(readLong(sc));
        if (member == null) {
            System.out.println("회원을 찾을 수 없습니다.");
            return;
        }

        printMember(member);
    }

    private static void findMemberByEmail(Scanner sc) {
        System.out.print("이메일: ");
        Member member = memberService.getMember(sc.nextLine());
        if (member == null) {
            System.out.println("회원을 찾을 수 없습니다.");
            return;
        }

        printMember(member);
    }

    private static void printMember(Member member) {
        System.out.printf("[%d] %s - %s%n",
                member.getId(),
                member.getEmail(),
                member.getNickname());
    }

    private static void findPostById(Scanner sc) {
        System.out.print("글번호: ");
        printPost(postService.getPostById(sc.nextLine()));
    }

    private static void searchPostsByTitle(Scanner sc) {
        System.out.print("제목 검색어: ");
        printPostList(postService.getPostByTitle(sc.nextLine()));
    }

    private static void searchPostsByContent(Scanner sc) {
        System.out.print("내용 검색어: ");
        printPostList(postService.getPostByContent(sc.nextLine()));
    }

    private static int readInt(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("숫자를 입력하세요: ");
            }
        }
    }

    private static Long readLong(Scanner sc) {
        while (true) {
            try {
                return Long.parseLong(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("숫자를 입력하세요: ");
            }
        }
    }
}
