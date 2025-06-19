import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom"; 
import Navbar from "./components/Navbar";
import Navbar2 from "./components/Navbar2";
import Page1 from "./components/Page1";
import Faculty from "./components/Faculty_Page"; 
import Student from "./components/AIML";
import Admin from "./components/Admin";
import AdminDashboard from "./components/AdminDashboard";
import Institute from "./components/Institute";
import Department from "./components/Department";
import { InputElement } from "./components/ui/InputElement";
import MouDashboard from "../Tabs/MouDashboard";
import AdminDashboardDemo from "./components/AdminDashBoardDemo";
import FileUploader from "./components/FileUploader";

// import { FacultyStudentFrom } from "./components/FacultyStudentForm";

const App = () => {
  return (

    // <AdminDashboardDemo />
    <AdminDashboard />
    // <Faculty />

    // <Router>
    //   {" "}
    //   <div>
    //     <Navbar />
    //     <Navbar2 />
    //     <Routes>
    //       <Route path="/" element={<Page1 />} />
    //       <Route path="/institute" element={<Institute/>}/>;
    //       <Route path="/department" element={<Department/>}/>;
    //       <Route path="/faculty" element={<Faculty />} />
    //       <Route path="/students" element={<Student />} />          
    //       <Route path="/admin" element={<Admin />} />
    //       <Route path="/admindashboard" element={<AdminDashboard />} />
    //     </Routes>
    //   </div>
    // </Router>
  );
};

export default App;