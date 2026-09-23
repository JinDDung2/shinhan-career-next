import { Route, Routes } from "react-router-dom";
import Header from "./components/Header";
import BoardDetailPage from "./components/BoardDetailPage";
import BoardFormPage from "./components/BoardFormPage";
import BoardListPage from "./components/BoardListPage";
import LoginPage from "./components/LoginPage";
import SignupPage from "./components/SignupPage";

function App() {
  return (
    <>
      <Header />
      <main>
        <Routes>
          <Route path="/" element={<BoardListPage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/signup" element={<SignupPage />} />
          <Route path="/boards/new" element={<BoardFormPage />} />
          <Route path="/boards/:id" element={<BoardDetailPage />} />
          <Route path="/boards/:id/edit" element={<BoardFormPage />} />
        </Routes>
      </main>
    </>
  );
}

export default App;
