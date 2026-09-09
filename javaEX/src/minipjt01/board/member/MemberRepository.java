package minipjt01.board.member;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MemberRepository {

    //회원들을 저장하는 자료구조
    private static List<Member> members = new ArrayList<>();
    private static final String FILE_NAME = "members.dat";

    //회원 번호로 회원 검색
    public Member findById(Long memberId){
        return members.stream()
                .filter(member -> member.getId().equals(memberId))
                .findFirst()
                .orElse(null);
    }

    //전체 회원 검색
    public List<Member> findAll(){
        return new ArrayList<>(members);
    }

    //회원 등록
    boolean save(Member member){
        if (member == null) {
            return false;
        }
        return members.add(member); // 회원 등록을 성공하면 true를 리턴해야 합니다.
    }

    //회원 이메일로 회원 검색
    public Member findByEmail(String email){
        return members.stream()
                .filter(member -> member.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    // 파일에 members  즉,  ArraryList 객체를 저장합니다.
    //(참고, ArraryList 객체 하나만 객체 직렬화해서 파일에 저장하면 그 안의 모든 member 객체가 같이 저장됨/
    boolean saveToFile(){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(members);
        } catch (IOException e) {
            System.out.println("파일 저장 실패: " + e.getMessage());
            return false;
        }
        return true ;
    }

    // 파일에서 ArraryList 객체를 읽어서 members에 로드 합니다.
    //(참고, ArraryList 객체 하나만 파일에서 읽어서 객체 역직렬화해서 그 안의 모든 member 객체가 같이 역직렬화됨
    boolean loadToFile(){
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return false;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Object object = in.readObject();
            if (!(object instanceof ArrayList<?> loadedMembers)) {
                return false;
            }

            members.clear(); // 기존 컬렉션을 비우고
            for (Object member : loadedMembers) {
                members.add((Member) member);
            }
        }  catch (IOException | ClassNotFoundException e) {
            System.out.println("파일 불러오기 실패: " + e.getMessage());
            return false;
        }

        return true ;
    }

}
