import { useParams } from "react-router-dom";

export default function StudentDetail() {
  const params = useParams();
  console.log(params); // 전체 파라미터 값

  // const id = useParams().id;   //  id 파라미터 값만
  const { id } = useParams(); //  id 파라미터 값만
  console.log(id);

  return (
    <>
      <h1> {id} 번 학생에 대한 정보 상세보기 페이지 입니다.</h1>
    </>
  );
}
