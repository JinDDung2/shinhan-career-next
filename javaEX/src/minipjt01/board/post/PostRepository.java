package minipjt01.board.post;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PostRepository {

    //게시판 글을 저장하는 자료구조
    private static List<Post> board = new ArrayList<>();
    private static final String FILE_NAME = "board.dat";

    //게시글 등록
    boolean save(Post post){
        if (post == null) {
            return false;
        }
        return board.add(post); // 게시글 등록을 성공하면 true를 리턴해야 합니다.
    }

    Post findById(Long postId){
        return board.stream()
                .filter(post -> post.getId().equals(postId))
                .findFirst()
                .orElse(null);
    }

    Post findById(String postId){
        try {
            return findById(Long.parseLong(postId));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public List<Post> findAll(){
        return new ArrayList<>(board);
    }

    // 제목에 매개인자의 키워드가 포함된 경우의 게시글 목록
    List<Post> findTitleContaining(String keyword){
        return board.stream()
                .filter(post -> post.getTitle().contains(keyword))
                .toList();
    }

    List<Post> findContentContaining(String keyword){
        return board.stream()
                .filter(post -> post.getContent().contains(keyword))
                .toList();
    }

    public boolean deleteById(Long postId) {
        Post post = findById(postId);
        if (post == null) {
            return false;
        }
        post.setStatus(PostStatus.DELETED);

        return true;
    }

    // 파일에 board(게시판 글을 저장하는 자료구조)즉,ArraryList 객체를 저장합니다.
    //(참고, ArraryList 객체 하나만 객체 직렬화해서 파일에 저장하면 그 안의 모든 post 객체가 같이 저장됨/

    boolean saveToFile(){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(board);
        } catch (IOException e) {
            System.out.println("파일 저장 실패: " + e.getMessage());
            return false;
        }
        return true ;
    }

    // 파일에서 ArraryList 객체를 읽어서 board(게시판 글을 저장하는 자료구조)에 로드 합니다.
    //(참고, ArraryList 객체 하나만 파일에서 읽어서 객체 역직렬화해서 그 안의 모든 post 객체가 같이 역직렬화됨
    boolean loadToFile(){
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return false;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Object object = in.readObject();
            if (!(object instanceof ArrayList<?> loadedPosts)) {
                return false;
            }

            board.clear(); // 기존 컬렉션을 비우고
            for (Object post : loadedPosts) {
                board.add((Post) post);
            }

        }  catch (IOException | ClassNotFoundException e) {
            System.out.println("파일 불러오기 실패: " + e.getMessage());
            return false;
        }

        return true ;
    }

}
