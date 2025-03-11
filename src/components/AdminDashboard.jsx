import React, { useState, useEffect } from "react";
import axios from "axios";
import { Button } from "@mui/material";



// API functions
const fetchDataFromApi = async (endpoint, method = "GET", data = null) => {
  const config = {
    method,
    url: `http://localhost:8080${endpoint}`,
    headers: { "Content-Type": "application/json" },
    data,
  };
  const response = await axios(config);
  return response.data;
};

// API endpoints
const apiEndpoints = {
  faculty: "/faculty",
  student: "/student",
  researchpapers: "/faculty-research-papers",
  conference: "/faculty-conferences",
  awards: "/faculty-awards", // New endpoint for awards
  developmentprogram: "/faculty-fdps",
  patents: "/faculty-patents",
  certificate: "/certificates",
  hackathon: '/hackathons',
  placement: "/student-placements",
  internship: "/internships",
  researchpaper: "/research-papers",
  sports: "/sports"



};

const AdminDashboard = () => {
  const [activeTab, setActiveTab] = useState("faculty");
  const [subTab, setSubTab] = useState(null);
  const [data, setData] = useState([]);
  const [newEntry, setNewEntry] = useState({});
  const [editing, setEditing] = useState(null);

  // Fetch data based on active tab
  const fetchData = async () => {
    try {
      const endpoint = subTab ? apiEndpoints[subTab.toLowerCase()] : apiEndpoints[activeTab];
      if (!endpoint) return;
      const res = await fetchDataFromApi(endpoint);
      setData(res);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  useEffect(() => {
    fetchData();
  }, [activeTab, subTab]);

  // Handle form submission for adding/updating entries
  const handleAddOrUpdate = async (e) => {
    e.preventDefault();
    try {
      const tabKey = subTab || activeTab;
      const payload = editing ? { ...newEntry, id: editing.id } : newEntry;
      const method = editing ? "PUT" : "POST";
      const url = editing ? `${apiEndpoints[tabKey]}/${editing.id}` : apiEndpoints[tabKey]; // Append ID for PUT

      await fetchDataFromApi(url, method, payload);
      fetchData();
      setNewEntry({});
      setEditing(null);
    } catch (error) {
      console.error("Error adding/updating entry", error);
    }
  };


  // Handle delete action
  const handleDelete = async (id) => {
    if (!window.confirm("Are you sure you want to delete this entry?")) return;
    try {
      const tabKey = subTab || activeTab;
      await fetchDataFromApi(`${apiEndpoints[tabKey]}/${id}`, "DELETE");
      fetchData();
    } catch (error) {
      console.error("Error deleting entry", error);
    }
  };

  const handleEdit = (item) => {
    setNewEntry({ ...item }); // Ensure a new object reference
    setEditing(item);
  };
  const facultyTabs = ["researchpaper", "conference", "awards", "developmentprogram", "patents"];
  const studentTabs = ["certificate", "hackathon", "placement", "internship", "researchpaper", "sports"];
  

  // Get table headers based on active tab
  const getTableHeaders = () => {
    const tab = subTab || activeTab;
    switch (tab) {
      case "faculty":
        return ["ID", "Name", "Email", "Department", "Actions"];
      case "student":
        return ["ID", "Name", "Email", "Year", "CGPA", "Branch", "Course", "Actions"];
      case "researchpapers":
        return ["ID", "Faculty Name", "Title", "Publication Date", "Journal Name", "Co-Authors", "Actions"];
      case "conference":
        return ["ID", "Faculty Name", "Conference Name", "Paper Title", "Presentation Date", "Actions"];
      case "awards":
        return ["ID", "Faculty Name", "Award Name", "Awarded By", "Award Date", "Actions"];
      case "developmentprogram":
        return ["ID", "Faculty Name", "Program Name", "Organized By", "Start Date", "End Date", "Actions"];
      case "patents":
        return ["ID", "Faculty Name", "Patent Title", "Patent Number", "Application Date", "Status", "Actions"];
      case "certificate":
        return ["ID", "Student Name", "Certificate Name", "Issued By", "Issue Date", "Certificate Link", "Actions"];
      case "hackathon":
        return ["ID", "Student Name", "Event Name", "Date", "Position", "Certificate Link", "Actions"];
      case "placement":
        return ["ID", "Student Name", "Company Name", "Joining Date", "Offer Letter Link", "Package", "Actions"];
      case "internship":
        return ["ID", "Student Name", "Company Name", "Role", "Start Date", "End Date", "Certificate Link", "Actions"];
      case "researchpaper":
        return ["ID", "Student Name", "Title", "Publication Date", "Journal Name", "Co-Authors", "Actions"];
      case "sports":
        return ["ID", "Student Name", "Sport Name", "Achievement", "Event Date", "Actions"];
      default:
        return [];
    }
  };

  // Get table row data based on active tab
  const getTableRowData = (item) => {
    const tab = subTab || activeTab;
    switch (tab) {
      case "faculty":
        return [item.id, item.name, item.email, item.department];
      case "student":
        return [item.id, item.name, item.email, item.year, item.cgpa, item.branch, item.course];
      case "researchpapers":
        return [item.id, item.faculty_name, item.title, item.publication_date, item.journal_name, item.co_authors];
      case "conference":
        return [item.id, item.faculty_name, item.conference_name, item.paper_title, item.presentation_date];
      case "awards":
        return [item.id, item.faculty_name, item.award_name, item.awarded_by, item.award_date];
      case "developmentprogram":
        return [item.id, item.faculty_name, item.program_name, item.organized_by, item.start_date, item.end_date];
      case "patents":
        return [item.id, item.faculty_name, item.patent_title, item.patent_number, item.application_date, item.status];
      case "certificate":
        return [item.id, item.student_name, item.certificate_name, item.issued_by, item.issue_date, item.certificate_link];
      case "hackathon":
        return [item.id, item.student_name, item.event_name, item.date, item.position, item.certificate_link];
      case "placement":
        return [item.id, item.student_name, item.company_name, item.joining_date, item.offer_letter_link, item.package];
      case "internship":
        return [item.id, item.student_name, item.company_name, item.role, item.start_date, item.end_date, item.certificate_link];
      case "researchpaper":
        return [item.id, item.student_name, item.title, item.publication_date, item.journal_name, item.co_authors];
      case "sports":
        return [item.id, item.student_name, item.sport_name, item.achievement, item.event_date];
      default:
        return [];
    }
  };

  // Get form fields based on active tab
  const getFormFields = () => {
    const tab = subTab || activeTab;
    switch (tab) {
      case "faculty":
        return ["name", "email", "department"];
      case "student":
        return ["name", "email", "year", "cgpa", "branch", "course"];
      case "researchpapers":
        return ["faculty_name", "title", "publication_date", "journal_name", "co_authors"];
      case "conference":
        return ["faculty_name", "conference_name", "paper_title", "presentation_date"];
      case "awards":
        return ["faculty_name", "award_name", "awarded_by", "award_date"];
      case "developmentprogram":
        return ["faculty_name", "program_name", "organized_by", "start_date", "end_date"];
      case "patents":
        return ["faculty_name", "patent_title", "patent_number", "application_date", "status"];
      case "certificate":
        return ["student_name", "certificate_name", "issued_by", "issue_date", "certificate_link"];
      case "hackathon":
        return ["student_name", "event_name", "date", "position", "certificate_link"];
      case "placement":
        return ["student_name", "company_name", "joining_date", "offer_letter_link", "package"];
      case "internship":
        return ["student_name", "company_name", "role", "start_date", "end_date", "certificate_link"];
      case "researchpaper":
        return ["student_name", "title", "publication_date", "journal_name", "co_authors"];
      case "sports":
        return ["student_name", "sport_name", "achievement", "event_date"];
      default:
        return [];
    }
  };



  return (
    <div className="p-6 bg-gray-100 text-black min-h-screen">
      <h2 className="text-2xl font-semibold mb-4">Admin Dashboard</h2>
      {/* Main Tabs */}
      <div className="flex space-x-4 mb-4">
        <Button
          className={`px-4 py-2 text-base font-semibold rounded ${activeTab === "faculty" ? "bg-blue-500 text-white" : "bg-gray-200"}`}
          onClick={() => { setActiveTab("faculty"); setSubTab(null); }}
        >
          Faculty
        </Button>
        <Button
          className={`px-4 py-2 text-base font-semibold rounded ${activeTab === "student" ? "bg-blue-500 text-white" : "bg-gray-200"}`}
          onClick={() => { setActiveTab("student"); setSubTab(null); }}
        >
          Student
        </Button>
      </div>

      {/* Sub-tabs for Faculty and Student */}
      {activeTab === "faculty" && (
        <div className="flex space-x-4 mb-4">
          {facultyTabs.map((tab) => (
            <Button
              key={tab}
              className={`px-4 py-2 text-base font-semibold rounded ${subTab === tab ? "bg-green-500 text-white" : "bg-gray-200"}`}
              onClick={() => setSubTab(tab)}
            >
              {tab.charAt(0).toUpperCase() + tab.slice(1)}
            </Button>
          ))}
        </div>
      )}

      {activeTab === "student" && (
        <div className="flex space-x-4 mb-4">
          {studentTabs.map((tab) => (
            <Button
              key={tab}
              className={`px-4 py-2 text-base font-semibold rounded ${subTab === tab ? "bg-green-500 text-white" : "bg-gray-200"}`}
              onClick={() => setSubTab(tab)}
            >
              {tab.charAt(0).toUpperCase() + tab.slice(1)}
            </Button>
          ))}
        </div>
      )}

      {/* Display selected tab content */}
      <div>
        {subTab && <h2 className="text-xl font-bold">{subTab.charAt(0).toUpperCase() + subTab.slice(1)} Content</h2>}
      </div>
  

      {/* Form */ }
  <form onSubmit={handleAddOrUpdate} className="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
    {getFormFields().map((field) => (
      <input
        key={field}
        type="text"
        placeholder={field.split("_").join(" ").toUpperCase()}
        value={newEntry[field] ?? ""} // Ensures no undefined value
        onChange={(e) => setNewEntry({ ...newEntry, [field]: e.target.value })}
        className="border p-2 rounded"
        required
      />

    ))}
    <button type="submit" className="px-4 py-2 text-base font-semibold bg-[#00062B] text-white rounded">
      {editing ? "Update" : "Add"}
    </button>
  </form>

  {/* Table */ }
  <table className="min-w-full bg-white">
    <thead className="bg-gray-800 text-white">
      <tr>
        {getTableHeaders().map((header) => (
          <th key={header} className="py-2">{header}</th>
        ))}
      </tr>
    </thead>
    <tbody>
      {data.map((item) => (
        <tr key={item.id} className="text-center border-b">
          {getTableRowData(item).map((value, index) => (
            <td key={index} className="py-2">{value}</td>
          ))}
          <td className="py-2 flex justify-center space-x-2">
            <button
              onClick={() => handleEdit(item)}
              className="bg-yellow-500 text-white px-2 py-1 rounded"
            >
              Edit
            </button>
            <button
              onClick={() => handleDelete(item.id)}
              className="bg-red-500 text-white px-2 py-1 rounded"
            >
              Delete
            </button>
          </td>
        </tr>
      ))}
    </tbody>
  </table>
    </div >
  );
};

export default AdminDashboard;
