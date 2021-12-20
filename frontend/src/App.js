import { BrowserRouter, Routes, Route } from "react-router-dom";
import Selector from "./components/Selector";
import Results from "./components/Results";
import LandingPage from "./components/LandingPage";

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<LandingPage />} />
          <Route path="/selector" element={<Selector />} />
          <Route path="/selector" element={<Results />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
