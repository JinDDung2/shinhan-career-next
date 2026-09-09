**미니 게시판 시스템 완성하기 — Member · Post · Repository · Service · CLI**

제공된 미완성 소스 코드(10개 파일)를 완성하여 회원 등록/글 등록/글 목록조회/글 삭제가 되는 콘솔 게시판을 완성합니다. 제공된 클래스명·메소드명·시그니처(매개변수/반환타입)는 그대로 유지한 채 주석으로 표시된 부분("이곳을 구현하세요")만 채워 넣으세요. 필요하다고 생각하는 메소드나 필드를 추가로 생성해도 됩니다.


**제공 파일 구조**

| minipjt01.board   └ BoardCLI.java              (미완성 · 콘솔 메뉴 실행) minipjt01.board.member   ├ Member.java                (완성됨)   ├ MemberRequestDto.java      (완성됨)   ├ MemberRepository.java      (미완성)   └ MemberService.java         (미완성) minipjt01.board.post   ├ Post.java                  (완성됨)   ├ PostStatus.java            (완성됨)   ├ PostRequestDto.java        (완성됨)   ├ PostRepository.java        (미완성)   └ PostService.java           (미완성 · 삭제 기능 추가 필요) |  |  |  |
| ----- | ----- | ----- | ----- |
| **STEP** | **내용** | **핵심 작업** |  |
| **1** | Repository 기본 기능 | findById·save·findByEmail·findTitleContaining |  |
| **2** | Service 계층 구현 | createMember/getMember, createPost/getPost |  |
| **3** | 파일 저장·불러오기(직렬화) | saveToFile / loadToFile |  |
| **4** | 글 삭제 기능 설계(신규) | PostStatus를 이용한 소프트 삭제 |  |
| **5** | CLI 메뉴 구현 | Scanner 기반 반복 메뉴 4종 연결 |  |
| **6** | 통합 테스트 | 전체 시나리오 실행·결과 확인 |  |

**STEP 1\. Repository 기본 기능 구현**   \[  · 난이도 ★★☆☆☆\]

**대상 파일 : MemberRepository.java, PostRepository.java**

**요구사항**

▪  MemberRepository.findById(Long): id가 일치하는 회원을 members에서 찾아 반환(없으면 null).

▪  MemberRepository.save(Member): members에 추가하고 true 반환(null이면 false).

▪  MemberRepository.findByEmail(String): email이 일치하는 회원을 반환(없으면 null).

▪  PostRepository.findById(String): 매개변수(String)를 Long으로 변환해 id가 일치하는 글을 반환.

▪  PostRepository.save(Post): board에 추가하고 true 반환.

▪  PostRepository.findTitleContaining(String): 제목에 keyword가 포함된 글만 담은 목록을 반환.

**해설**  findById류의 메소드는 컬렉션을 순회하며 조건에 맞는 객체를 찾는 전형적인 패턴입니다. PostRepository.findById는 매개변수 타입이 String이므로(CLI에서 문자열로 입력받는 상황을 고려), Long.parseLong()으로 변환 후 Post.id(Long)와 비교해야 합니다.

**STEP 2\. Service 계층 구현**   \[20분 · 난이도 ★★★☆☆\]

**대상 파일 : MemberService.java, PostService.java**

**요구사항**

▪  MemberService.createMember(Long, MemberRequestDto): dto의 값으로 Member를 만들어 저장하고, 성공 시 id를 반환.

▪  MemberService.getMember(Long) / getMember(String) / getMember(): 각각 회원번호·이메일·전체 조회로 위임.

▪  PostService.createPost(Long memberId, PostRequestDto dto): memberId로 작성자를 조회 후(없으면 null 반환) Post를 만들어 저장.

▪  PostService.getPost(Long) / getPost(String) / getPost(): 삭제되지 않은(NORMAL) 글만 걸러서 반환.

**해설**  Service는 Repository를 조합해 비즈니스 규칙을 적용하는 계층입니다. 예를 들어 '삭제된 글은 목록에 보이지 않아야 한다'는 규칙은 Repository가 아니라 Service의 getPost()에서 PostStatus.isVisible()로 걸러내는 방식으로 구현합니다. createPost에서 작성자가 존재하지 않으면 글을 등록하지 않고 null을 반환해 CLI가 실패를 알 수 있게 합니다.

**STEP 3\. 파일 저장 · 불러오기 (객체 직렬화)**   \[15분 · 난이도 ★★★☆☆\]

**대상 파일 : MemberRepository.java, PostRepository.java**

**요구사항**

▪  Member, Post는 이미 Serializable을 구현하고 있으므로 별도 수정이 필요 없습니다.

▪  saveToFile(): ArrayList 전체(members 또는 board)를 ObjectOutputStream으로 파일에 저장하세요.

▪  loadToFile(): 파일에서 ArrayList를 읽어와 기존 컬렉션을 비우고 다시 채우세요.

**해설**  java.io.\*를 import해야 합니다(FileOutputStream, ObjectOutputStream 등). ArrayList 하나를 통째로 writeObject()하면 그 안의 모든 Post/Member 객체가 함께 직렬화됩니다 — 단, Post가 참조하는 Member(writer) 필드도 Serializable이어야 하는데 이미 그렇게 되어 있습니다. readObject()의 반환형은 Object이므로 (ArrayList\<Member\>)로 형변환이 필요합니다.

**STEP 4\. 글 삭제 기능 설계 (신규 메소드 추가)**   \[15분 · 난이도 ★★★☆☆\]

**대상 파일 : PostService.java (deletePost 메소드 새로 추가)**

**요구사항**

▪  제공된 파일에는 '삭제' 기능이 아예 없습니다 — BoardCLI 메뉴 4번 '글 삭제'를 지원하려면 직접 메소드를 추가해야 합니다.

▪  실제로 목록에서 글을 지우지 말고(하드 삭제 X), PostStatus.DELETED로 상태만 바꾸는 소프트 삭제로 구현하세요.

▪  이렇게 하면 Step 2에서 만든 getPost()가 자동으로 삭제된 글을 걸러줍니다(isVisible() 재사용).

▪  public boolean deletePost(Long postId) 형태로 PostService에 추가하고, 존재하지 않는 글번호면 false를 반환하세요.

**해설**  소프트 삭제는 실무에서 자주 쓰는 패턴입니다. 데이터를 실제로 지우면 복구가 불가능하고 통계·이력 관리도 어렵기 때문에, 상태값(PostStatus)만 바꿔서 '보이지 않게' 처리합니다. 이미 PostStatus에 DELETED와 isVisible()이 준비되어 있었던 이유가 바로 이것입니다.

**STEP 5\. CLI 메뉴 구현**   \[30분 · 난이도 ★★★★☆\]

**대상 파일 : BoardCLI.java**

**요구사항**

▪  Scanner로 사용자 입력을 받는 무한 반복 메뉴를 만드세요(0번 입력 시 종료).

▪  1\. 전체 글 보기 → postService.getPost()로 삭제되지 않은 글만 출력(글번호/제목/작성자/등록일시).

▪  2\. 회원 등록 → 이메일/비밀번호/닉네임을 입력받아 memberService.createMember() 호출.

▪  3\. 글 등록 → 작성자 회원번호/제목/내용을 입력받아 postService.createPost() 호출.

▪  4\. 글 삭제 → 글번호를 입력받아 Step 4에서 만든 postService.deletePost() 호출.

▪  기존에 있던 private static ArrayList\<Post\> board 필드는 더 이상 필요 없습니다(Service·Repository가 저장을 담당) — 삭제해도 됩니다.

**해설**  Repository/Service의 접근제한자가 default(package-private)인 메소드(예: save, findById(String))는 같은 패키지에서만 호출할 수 있습니다. BoardCLI는 minipjt01.board 패키지이므로 minipjt01.board.post의 default 메소드를 직접 호출할 수 없고, 반드시 public인 Service의 메소드를 통해서만 접근해야 합니다 — 이것이 Repository/Service/CLI로 계층을 나눈 이유(캡슐화)입니다.

**STEP 6\. 통합 테스트 — 전체 시나리오 실행**   \[20분 · 난이도 ★★★☆☆\]

**대상 파일 : 전체**

**요구사항**

▪  프로그램을 실행하여 아래 순서로 입력했을 때 예상 실행결과와 같은지 확인하세요.

▪  ① 회원 등록(2번, 홍길동) → ② 글 등록(3번, 회원번호 1\) → ③ 전체 글 보기(1번) → ④ 글 삭제(4번) → ⑤ 전체 글 보기(1번) → ⑥ 종료(0번)

**예상 콘솔 입출력**

| 실행결과 메뉴 선택: 2 이메일: hong@test.com  비밀번호: 1234  닉네임: 홍길동 회원 등록 완료\! 회원번호: 1 메뉴 선택: 3 작성자 회원번호: 1  제목: 안녕하세요  내용: 첫 글입니다 글 등록 완료\! 글번호: 1 메뉴 선택: 1 \[1\] 안녕하세요 \- 홍길동 (2026-09-08T12:00:00.123) 메뉴 선택: 4 삭제할 글번호: 1 삭제 완료 메뉴 선택: 1 등록된 글이 없습니다. 메뉴 선택: 0 프로그램을 종료합니다. |
| :---- |

**해설**  삭제 후 같은 글이 목록에서 사라지는 것은 실제로 지워진 것이 아니라 PostStatus가 DELETED로 바뀌어 PostService.getPost()의 isVisible() 필터에 걸러졌기 때문입니다. 시간(등록일시)은 실행 시각에 따라 달라지는 것이 정상입니다.

*제출: 완성한   java 파일 전체를 압축하여 제출하세요. 시간이 남으면 findByEmail로 이메일 중복 회원가입을 막는 예외처리를 추가해보세요.*