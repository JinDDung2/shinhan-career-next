import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { useRef } from "react";

export default function Student() {
  const backendDomain = import.meta.env.VITE_BACKEND_DOMAIN;
  const [student, setStudent] = useState({
    id: 0,
    name: null,
    grade: 0,
    marks: {
      maths: 0,
      physics: 0,
      chemistry: 0,
    },
  });

  const navigate = useNavigate();
  const { id } = useParams();
  const mathScoreRef = useRef(0);

  useEffect(() => {
    console.log(id);
    //GET Method (default)
    fetch(`${backendDomain}/students/${id}`)
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        console.log(data);
        setStudent(data);
      });
  }, [id]);

  function updateStudent() {
    student.marks.maths = mathScoreRef.current.value;
    let newStudent = { ...student };

    fetch(`${backendDomain}/students/${id}`, {
      method: "Put", // 갱신을 위해 Put Method 로 요청
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(newStudent),
    }).then((res) => {
      if (res.ok) {
        alert("수정 성공 ");
        setStudent(newStudent); //state 변경으로 화면 갱신
      }
    });
  }
  function deleteStudent() {
    if (window.confirm("정말로 삭제하시겠습니까?")) {
      fetch(`${backendDomain}/students/${id}`, {
        method: "Delete",
        headers: {
          "Content-Type": "application/json",
        },
      }).then(() => {
        navigate("/");
      });
    }
  }
  return (
    <div>
      <table border={1}>
        <tbody>
          <tr>
            <th>학번</th>
            <th>이름</th>
            <th>수학점수</th>
            <th>수정하려면 새 값을 입력하고 버튼 클릭</th>
            <th>삭제하려면 삭제버튼을 클릭하세요</th>
          </tr>
          <tr key={student.id}>
            <td>{student.id}</td>
            <td>{student.name}</td>
            <td>{student.marks.maths} </td>
            <td>
              <input type="number" min="0" max="100" ref={mathScoreRef} />
              <button onClick={updateStudent}>수정</button>
            </td>
            <td>
              <button onClick={deleteStudent}>Delete</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  );
}
