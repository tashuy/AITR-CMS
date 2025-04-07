import React, { useState, useEffect } from "react";
import axios from "axios";
import { Button } from "@mui/material";



const fetchDataFromApi = async (endpoint, method = "GET", data = null) => {
  try {
    const adminUsername = localStorage.getItem("adminUsername");
    const config = {
      method,
      url: `http://localhost:8080${endpoint}`,
      headers: {
        "Content-Type": "application/json",
        ...(adminUsername && { username: adminUsername }),
      },
      data,
    };
    console.log(`Hitting: ${method} ${config.url}`);
    const response = await axios(config);
    console.log("Response:", response);
    return response.data;
  } catch (err) {
    console.error("API Error:", err?.response?.data || err.message);
    throw err;
  }
};



// API endpoints
const apiEndpoints = {
  faculty: "/admin/faculty",
  student: "/student",
  researchpapers: "/faculty-research-papers",
  conference: "/faculty-conferences",
  awards: "/admin/faculty-awards", // New endpoint for awards
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
  const [activeTab, setActiveTab] = useState(localStorage.getItem("activeTab") || "faculty");
  const [subTab, setSubTab] = useState(null);
  const [data, setData] = useState([]);
  const [newEntry, setNewEntry] = useState({});
  const [editing, setEditing] = useState(null);


  const handleTabChange = (tab) => {
    setActiveTab(tab);
    localStorage.setItem("activeTab", tab);
  };

  // Fetch data based on active tab
  const fetchData = async () => {
    try {
      const tabKey = subTab || activeTab;
      const endpoint = editing ? `${apiEndpoints[tabKey]}/${newEntry.id}` : apiEndpoints[tabKey];

      if (!endpoint) return;
      const res = await fetchDataFromApi(endpoint);
      setData(res);
      console.log("API Response:", res);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  useEffect(() => {
    fetchData();
  }, [activeTab, subTab]);

  // Handle form submission for adding/updating entries
  const handleAddOrUpdate = async () => {
    try {
      console.log("Submitting new entry:", newEntry); // Check what you're sending
      const payload = { ...newEntry };
      const tabKey = subTab || activeTab;
      const endpoint = editing
        ? `${apiEndpoints[tabKey]}/${newEntry.id}`
        : apiEndpoints[tabKey];

      const method = editing ? "PUT" : "POST";

      //console.log("Calling API:", method, endpoint, payload); // Log API info
      console.log("ACTIVE TAB:", activeTab);
      console.log("TAB KEY:", subTab || activeTab);
      console.log("Endpoint Used:", endpoint);
      console.log("Payload:", payload);


      await fetchDataFromApi(endpoint, method, payload); // Actual call
      setNewEntry({});
      setEditing(false);
      
      // fetchEntries(); // Refresh entries
    } catch (error) {
      console.error("Error adding/updating entry:", error?.response?.data || error.message);
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
    console.log("Editing entry:", item);
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
        return ["ID", "NAME", "EMAIL", "DEPARTMENT", "MOBILE NUMBER", "YEARS OF EXPERIENCE", "DESIGNATION", "Actions"];
      case "student":
        return ["ID", "Name", "Email", "Year", "CGPA", "Branch", "Course", "Actions"];
      case "researchpapers":
        return ["ID", "Faculty Name", "Title", "Publication Date", "Journal Name", "Co-Authors", "Actions"];
      case "conference":
        return ["ID", "Faculty Name", "Conference Name", "Paper Title", "Presentation Date", "Conference Type", "Conference Location", "Conference Mode", "Publication Status", "Journal Name", "ISSN Number", "Indexing", "Certificate Link", "Actions"];

      case "awards":
        return [
          "ID",
          "Faculty Name",
          "Award Name",
          "Awarded By",
          "Award Date",
          "Category",
          "Recognition Type",
          "Event Name",
          "Description",
          "Certificate Link",
          "Actions"
        ];

      case "developmentprogram":
        return ["ID", "FACULTY NAME", "PROGRAM NAME", "ORGANIZED BY", "START DATE", "END DATE", "PROGRAM TYPE", "MODE", "LOCATION", "DURATION DAYS", "CERTIFICATE LINK", "ACTIONS"];

      case "patents":
        return ["ID", "Faculty Name", "Patent Title", "Patent Number", "Application Date", "Status", "Actions"];
      case "certificate":
        return ["ID", "Student Name", "Enrollment Number", "Certificate Name", "Certificate Type", "Issued By", "Issue Date", "Validity Period", "Grade Or Score", "Certificate Description", "Mode Of Training", "Related Course Or Program", "Certificate Status", "Verified", "Certificate Link", "Actions"];


      case "hackathon":
        return ["ID", "STUDENT NAME", "ENROLLMENT NUMBER", "EVENT NAME", "DATE", "TEAM NAME", "TEAM SIZE", "MENTOR NAME", "HACKATHON TYPE", "ORGANIZING BODY", "VENUE", "PROBLEM STATEMENT", "TECHNOLOGY USED", "PRIZE MONEY", "SPONSORING COMPANY", "POSITION", "PROJECT GITHUB LINK", "PROJECT DESCRIPTION", "CERTIFICATE STATUS", "CERTIFICATE LINK", "ACTIONS"];

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
        return [item.id, item.name, item.email, item.department, item.mobile_no, item.years_Of_Experience, item.designation];
      case "student":
        return [item.id, item.name, item.email, item.year, item.cgpa, item.branch, item.course];
      case "researchpapers":
        return [item.id, item.faculty_name, item.title, item.publication_date, item.journal_name, item.co_authors];
      case "conference":
        return [item.id, item.facultyName, item.conferenceName, item.paperTitle, item.presentationDate, item.conferenceType, item.conferenceLocation, item.conferenceMode, item.publicationStatus, item.journalName, item.issnNumber, item.indexing, item.certificateLink];

      case "awards":
        return [item.id, item.facultyName, item.awardName, item.awardedBy, item.awardDate, item.category, item.recognitionType, item.eventName, item.description, item.certificateLink];

      case "developmentprogram":
        return [item.id, item.facultyName, item.programName, item.organizedBy, item.startDate, item.endDate, item.programType, item.mode, item.location, item.durationDays, item.certificateLink];

      case "patents":
        return [item.id, item.faculty_name, item.patent_title, item.patent_number, item.application_date, item.status];
      case "certificate":
        return [item.id, item.studentName, item.enrollmentNumber, item.certificateName, item.certificateType, item.issuedBy, item.issueDate, item.validityPeriod, item.gradeOrScore, item.certificateDescription, item.modeOfTraining, item.relatedCourseOrProgram, item.certificateStatus, item.verified, item.certificateLink];

      case "hackathon":
        return [item.id, item.studentName, item.enrollmentNumber, item.eventName, item.date, item.teamName, item.teamSize, item.mentorName, item.hackathonType, item.organizingBody, item.venue, item.problemStatement, item.technologyUsed, item.prizeMoney, item.sponsoringCompany, item.position, item.projectGithubLink, item.projectDescription, item.certificateStatus, item.certificateLink];

      case "placement":
        return [item.id, item.student_name, item.company_name, item.joining_date, item.offer_letter_link, item.package];
      case "internship":
        return [item.id, item.student_name, item.enrollment_number, item.company_name, item.role, item.internship_type, item.stipend, item.duration, item.department, item.mentor_name, item.mentor_email, item.technologies_used, item.project_name, item.project_description, item.skills_gained, item.company_location, item.internship_status, item.start_date, item.end_date, item.offer_letter_link, item.experience_letter_link, item.certificate_link];

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
        return ["name", "email", "department", "mobile_no", "years_Of_Experience", "designation"];

      case "student":
        return ["name", "email", "year", "cgpa", "branch", "course"];
      case "researchpapers":
        return ["faculty_name", "title", "publication_date", "journal_name", "co_authors"];
      case "conference":
        return ["faculty_name", "conference_name", "paper_title", "presentation_date", "conference_type", "conference_location", "conference_mode", "publication_status", "journal_name", "issn_number", "indexing", "certificate_link"];

        case "awards":
          return [
            "facultyName",
            "awardName",
            "awardedBy",
            "awardDate",
            "category",
            "recognitionType",
            "eventName",
            "description",
            "certificateLink"
          ];
        

      case "developmentprogram":
        return ["faculty_name", "program_name", "organized_by", "start_date", "end_date", "program_type", "mode", "location", "duration_days", "certificate_link"];
      case "patents":
        return ["faculty_name", "patent_title", "patent_number", "application_date", "status"];
      case "certificate":
        return ["student_name", "enrollment_number", "certificate_name", "certificate_type", "issued_by", "issue_date", "validity_period", "grade_or_score", "certificate_description", "mode_of_training", "related_course_or_program", "certificate_status", "verified", "certificate_link"];
      case "hackathon":
        return ["id", "student_name", "enrollment_number", "event_name", "date", "team_name", "team_size", "mentor_name", "hackathon_type", "organizing_body", "venue", "problem_statement", "technology_used", "prize_money", "sponsoring_company", "position", "project_github_link", "project_description", "certificate_status", "certificate_link"];

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


      {/* Form */}
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

      {/* Table */}
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
