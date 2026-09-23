import { Route, Routes } from "react-router-dom";
import Header from "./components/Header";
import BoardDetailPage from "./pages/BoardDetailPage";
import BoardFormPage from "./pages/BoardFormPage";
import BoardListPage from "./pages/BoardListPage";
import LoginPage from "./pages/LoginPage";
import SignupPage from "./pages/SignupPage";

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
