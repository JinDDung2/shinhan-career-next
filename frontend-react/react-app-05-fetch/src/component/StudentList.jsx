import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

export default function StudentList() {
  const backendDomain = import.meta.env.VITE_BACKEND_DOMAIN;

  const [students, setStudents] = useState([]);

  useEffect(() => {
    //GET Method (default)
    fetch(`${backendDomain}/students`)
      .then((res) => res.json())
      .then((data) => {
        setStudents(data);
      });
  }, [backendDomain]);

  return (
    <div>
      <h2>학생 목록</h2>
      <table border={1}>
        <tbody>
          <tr>
            <th>학번</th>
            <th>이름</th>
            <th>학년</th>
            <th>수학점수</th>
            <th>물리점수</th>
            <th>화학점수</th>
          </tr>
          {students.map((student) => (
            <tr key={student.id}>
              <td>{student.id}</td>
              <td>
                <Link to={`/students/${student.id}`}>{student.name}</Link>
              </td>
              <td>{student.grade}</td>
              <td>{student.marks.maths}</td>
              <td>{student.marks.physics}</td>
              <td>{student.marks.chemistry}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <Link to="/students/new">
        <button>학생 등록</button>
      </Link>
    </div>
  );
}
