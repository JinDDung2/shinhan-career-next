import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function StudentRegister() {
  const backendDomain = import.meta.env.VITE_BACKEND_DOMAIN;
  const navigate = useNavigate();

  const [form, setForm] = useState({
    name: "",
    grade: "",
    maths: "",
    physics: "",
    chemistry: "",
  });

  function handleChange(e) {
    const { name, value } = e.target;
    setForm({ ...form, [name]: value });
  }

  function registerStudent(e) {
    e.preventDefault();

    // id는 넣지 않는다. json-server가 랜덤으로 id를 생성해준다.
    const newStudent = {
      name: form.name,
      grade: Number(form.grade),
      marks: {
        maths: Number(form.maths),
        physics: Number(form.physics),
        chemistry: Number(form.chemistry),
      },
    };

    fetch(`${backendDomain}/students`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(newStudent),
    }).then((res) => {
      if (res.ok) {
        alert("등록 성공");
        navigate("/"); // 등록 완료 후 전체 학생 목록 화면으로 이동
      }
    });
  }

  return (
    <div>
      <h2>학생 등록</h2>
      <form onSubmit={registerStudent}>
        <table border={1}>
          <tbody>
            <tr>
              <th>이름</th>
              <td>
                <input
                  type="text"
                  name="name"
                  value={form.name}
                  onChange={handleChange}
                  required
                />
              </td>
            </tr>
            <tr>
              <th>학년</th>
              <td>
                <input
                  type="number"
                  name="grade"
                  value={form.grade}
                  onChange={handleChange}
                  required
                />
              </td>
            </tr>
            <tr>
              <th>수학점수</th>
              <td>
                <input
                  type="number"
                  min="0"
                  max="100"
                  name="maths"
                  value={form.maths}
                  onChange={handleChange}
                  required
                />
              </td>
            </tr>
            <tr>
              <th>물리점수</th>
              <td>
                <input
                  type="number"
                  min="0"
                  max="100"
                  name="physics"
                  value={form.physics}
                  onChange={handleChange}
                  required
                />
              </td>
            </tr>
            <tr>
              <th>화학점수</th>
              <td>
                <input
                  type="number"
                  min="0"
                  max="100"
                  name="chemistry"
                  value={form.chemistry}
                  onChange={handleChange}
                  required
                />
              </td>
            </tr>
          </tbody>
        </table>
        <button type="submit">등록</button>
      </form>
    </div>
  );
}
