package minipjt01.board.post;


import minipjt01.board.member.Member;
import minipjt01.board.member.MemberRepository;

import java.util.List;

public class PostService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public PostService() {
        this(new MemberRepository(), new PostRepository());
    }

    public PostService(MemberRepository memberRepository, PostRepository postRepository) {
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
    }

    //게시글 등록
    public Long createPost(Long memberId, PostRequestDto req) {
        Member member = memberRepository.findById(memberId);
        if (member == null) {
            return null;
        }

        Post post = req.toPost(member);
        if (!postRepository.save(post)) {
            return null;
        }
        return post.getId();
    }

    // 글번호로 게시글 검색
    public Post getPost(Long postId) {
        Post post = postRepository.findById(postId);
        if (post == null || !post.getStatus().isVisible()) {
            return null;
        }
        return post;
    }

    public Post getPostById(String postId) {
        Post post = postRepository.findById(postId);
        if (post == null || !post.getStatus().isVisible()) {
            return null;
        }
        return post;
    }

    // 전체 글 (삭제되지 않은) 검색
    public List<Post> getPost() {
        return postRepository.findAll().stream()
                .filter(post -> post.getStatus().isVisible())
                .toList();
    }

    // 키워드로 글 (삭제되지 않은) 검색
    public List<Post> getPost(String keyword, boolean isTitle) {
        List<Post> posts;
        if (isTitle) {
            posts = postRepository.findTitleContaining(keyword);
        } else {
            posts = postRepository.findContentContaining(keyword);
        }

        return posts.stream()
                .filter(post -> post.getStatus().isVisible())
                .toList();
    }

    public List<Post> getPostByTitle(String keyword) {
        return getPost(keyword, true);
    }

    public List<Post> getPostByContent(String keyword) {
        return getPost(keyword, false);
    }

    public List<Post> getAllPost() {
        return getPost();
    }

    // 글 삭제
    public boolean deletePost(Long postId){
        return postRepository.deleteById(postId);  // 삭제 성공시 true 반환
    }

    public boolean saveToFile() {
        return postRepository.saveToFile();
    }

    public boolean loadToFile() {
        return postRepository.loadToFile();
    }
}
