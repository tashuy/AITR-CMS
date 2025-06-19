import React, { useState, useEffect, useRef } from "react";
import axios from "axios";
import { Button } from "@mui/material";
import Navbar from "./Navbar";
import { API_BASE_URL, apiEndpoints } from "../config";



const fetchDataFromApi = async (endpoint, method = "GET", data = null) => {
  try {
    const adminUsername = localStorage.getItem("adminUsername");
    // const url = endpoint
    const url = `http://localhost:8080${endpoint}`
    const config = {
      method,
      url: url,
      headers: {
        "Content-Type": "application/json",
        ...(adminUsername && { username: adminUsername }),
      },
      data,
    };
    console.log(`Hitting: ${method} ${config.url}`);
    const response = await axios.get(endpoint);
    
    console.log("Response:", response.data);
    return response.data;
  } catch (err) {
    console.error("API Error:", err?.response?.data || err.message);
    throw err;
  }
};



// API endpoints



const AdminDashboard = () => {
  const [activeTab, setActiveTab] = useState(localStorage.getItem("activeTab"));
  const [subTab, setSubTab] = useState(localStorage.getItem("subTab"));
  const [data, setData] = useState([]);
  const [newEntry, setNewEntry] = useState({});
  const [editing, setEditing] = useState(null);
  const [certificateFile, setFile] = useState(null)
  const previousTab = useRef();
  const previousSubtab = useRef();



  const handleTabChange = (tab) => {
    setActiveTab(tab);
    localStorage.setItem("activeTab", tab);
  };

  // Fetch data based on active tab
  const fetchData = async () => {
    try {
      const tabKey = subTab || activeTab;
      const endpoint = editing === "add"
        ? `${apiEndpoints[tabKey]}/${newEntry.id}`
        : apiEndpoints[tabKey];
      
      if (!endpoint) return;
      const  res  = await fetchDataFromApi(endpoint);
      
      console.log("res" ,res.response)
      setData(res.response);
      console.log("API Response:", res);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  useEffect(() => {
    previousTab.current = activeTab;
    previousSubtab.current = subTab;
    localStorage.setItem("subTab" , previousSubtab.current)
    localStorage.setItem("activeTab" ,previousTab.current)
    fetchData();
  }, [activeTab, subTab]);

  const handleDownloadCertificate = async (id) => {
    try {
      const response = await axios.get(`${API_BASE_URL}/sports/${id}/certificate`, {
        responseType: "blob", // ensures it's treated as a file
      });

    const blob = new Blob([response.data], { type: "application/pdf" });
    const downloadUrl = window.URL.createObjectURL(blob);

    const link = document.createElement("a");
    link.href = downloadUrl;
    link.download = `certificate-${id}.pdf`;
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);

    window.URL.revokeObjectURL(downloadUrl); // Clean up
  } catch (error) {
    console.error("Error downloading certificate:", error);
    alert("Failed to download certificate");
  }
};


// Handle form submission for adding/updating entries
const handleAddOrUpdate = async () => {
  // i am defining my own endpoint
  try {
    const tabKey = subTab || activeTab;
    const endpoint = `${apiEndpoints[tabKey]}/${newEntry.id}`
    const method = "POST"



    let formData = new FormData();

    if (tabKey && newEntry.certificateFile) {
      Object.entries(newEntry).forEach(([key, value]) => {
      if (value !== undefined && key !== "certificateFile") {
        formData.append(key, value);
        console.log("form data: " , formData)
      }
      });
    }
  formData.append("file", certificateFile); // file must match backend filed name
  formData.append("data", newEntry)



  const config = {
    method,
    url:`http://localhost:8080/${endpoint}`,
    headers: {
        "Content-Type": "multipart/form-data" 
        },  
  // data: tabKey === "sports" ? formData : newEntry,
    data: formData
  };

  await axios( config ).then(alert("Submitted"))

  setNewEntry({});
  setEditing(false);
  fetchData(endpoint); // Refresh data
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
  return ["Id", "Name", "Email", "Department", "Mobile Number", "Years Of Experience", "Designation", "Actions"];

case "student":
  return ["Id", "Name", "Email", "Year", "Course", "Branch", "Cgpa", "Date Of Birth", "Gender", "Admission Year", "Year Of Graduation", "Status", "Actions"];

case "researchpapers":
  return ["Id", "Faculty Name", "Title", "Publication Date", "Journal Name", "Co-Authors", "Actions"];

case "conference":
  return ["Id", "Faculty_Name", "Conference Name", "Paper Title", "Presentation Date", "Conference Type", "Conference Location", "Conference Mode", "Publication Status", "Journal Name", "Issn Number", "Indexing", "Certificate_Link", "Actions"];

case "awards":
  return ["Id", "Faculty Name", "Award Name", "Awarded By", "Award Date", "Category", "Recognition Type", "Event Name", "Description", "Certificate_Link", "Actions"];

case "developmentprogram":
  return ["Id", "Faculty Name", "Program Name", "Organized By", "Start Date", "End Date", "Program Type", "Mode", "Location", "Duration Days", "Certificate_Link", "Actions"];

case "patents":
  return ["Id", "Faculty Name", "Patent Title", "Patent Number", "Application Date", "Status", "Inventor Names", "Patent Type", "Patent Office", "Grant Date", "Expiry Date", "Country", "Patent Category", "Certificate_Link", "Actions"];

case "certificate":
  return ["Id", "Student Name", "Enrollment Number", "Certificate Name", "Certificate Type", "Issued By", "Issue Date", "Validity Period", "Grade Or Score", "Certificate Description", "Mode Of Training", "Related Course Or Program", "Certificate Status", "Verified", "Certificate_Link", "Actions"];

case "hackathon":
  return ["Id", "Student Name", "Enrollment Number", "Event Name", "Date", "Team Name", "Team Size", "Mentor Name", "Hackathon Type", "Organizing Body", "Venue", "Problem Statement", "Technology Used", "Prize Money", "Sponsoring Company", "Position", "Project Github Link", "Project Description", "Certificate Status", "Certificate_Link", "Actions"];

case "placement":
  return ["Id", "Student Name", "Company Name", "Job Role", "Branch", "Placement Type", "Package", "Joining Date", "Offer Letter Pdf", "Company Location", "Interview Mode", "Actions"];

case "internship":
  return ["Id", "Student Name", "Enrollment Number", "Company Name", "Role", "Internship Type", "Stipend", "Duration", "Department", "Mentor Name", "Mentor Email", "Technologies Used", "Project Name", "Project Description", "Skills Gained", "Company Location", "Internship Status", "Start Date", "End Date", "Offer Letter Link", "Experience Letter Link", "Certificate_Link", "Actions"];

case "researchpaper":
  return ["Id", "Student Name", "Title", "Publication Date", "Journal Name", "Co-Authors", "Actions"];

case "sports":
  return ["Id", "Student Name", "Sport Name", "Achievement", "Event Date", "Event Name", "Event Level", "Event Location", "Position", "Certificate", "Coach Name", "Actions"];

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
        return [item.id, item.name, item.email, item.year, item.course, item.branch, item.cgpa, item.dateOfBirth, item.gender, item.yearOfAdmission, item.yearOfGraduation, item.status];
      
    case "researchpapers":
      return [item.id, item.faculty_name, item.title, item.publication_date, item.journal_name, item.co_authors];
    case "conference":
      return [item.id, item.facultyName, item.conferenceName, item.paperTitle, item.presentationDate, item.conferenceType, item.conferenceLocation, item.conferenceMode, item.publicationStatus, item.journalName, item.issnNumber, item.indexing, item.certificatePdf];

    case "awards":
      return [item.id, item.facultyName, item.awardName, item.awardedBy, item.awardDate, item.category, item.recognitionType, item.eventName, item.description, item.certificatePdf];

    case "developmentprogram":
      return [item.id, item.facultyName, item.programName, item.organizedBy, item.startDate, item.endDate, item.programType, item.mode, item.location, item.durationDays, item.certificateLink];
    case "patents": return [item.id, item.facultyName, item.patentTitle, item.patentNumber, item.applicationDate, item.status, item.inventorNames, item.patentType, item.patentOffice, item.grantDate, item.expiryDate, item.country, item.patentCategory, item.certificatePdf];

    case "certificate":
      return [item.id, item.studentName, item.enrollmentNumber, item.certificateName, item.certificateType, item.issuedBy, item.issueDate, item.validityPeriod, item.gradeOrScore, item.certificateDescription, item.modeOfTraining, item.relatedCourseOrProgram, item.certificateStatus, item.verified, item.certificatePdf];

    case "hackathon":
      return [item.id, item.studentName, item.enrollmentNumber, item.eventName, item.date, item.teamName, item.teamSize, item.mentorName, item.hackathonType, item.organizingBody, item.venue, item.problemStatement, item.technologyUsed, item.prizeMoney, item.sponsoringCompany, item.position, item.projectGithubLink, item.projectDescription, item.certificateStatus, item.certificateLink];

    case "placement":
      return [item.id, item.studentName, item.companyName, item.jobRole, item.branch, item.placementType, item.packageAmount, item.joiningDate, <a href="/api/student-placement/pdf/${item.id}" target="_blank">View PDF</a>, item.companyLocation, item.interviewMode, "Actions"];
    case "internship":
      return [
        item.id,
        item.studentName,
        item.enrollmentNumber,
        item.companyName,
        item.role,
        item.internshipType,
        item.stipend,
        item.duration,
        item.department,
        item.mentorName,
        item.mentorEmail,
        item.technologiesUsed,
        item.projectName,
        item.projectDescription,
        item.skillsGained,
        item.companyLocation,
        item.internshipStatus,
        item.startDate,
        item.endDate,
        item.offerLetterLink,
        item.experienceLetterLink,
        <Button
          variant="contained"
          size="small"
          onClick={() => handleDownloadCertificate(item.id)}
        >
          Download
        </Button>
      ];
      
    case "researchpaper":
      return [item.id, item.student_name, item.title, item.publication_date, item.journal_name, item.co_authors];
    case "sports": return [item.id, item.studentName, item.sportName, item.achievement, item.eventDate, item.eventName, item.eventLevel, item.eventLocation, item.position,
    <Button
      variant="contained"
      size="small"
      onClick={() => handleDownloadCertificate(item.id)}
    >
      Download
    </Button>, item.coachName];

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
        return ["name", "email", "year", "course", "branch", "cgpa", "date_Of_Birth", "gender", "year_Of_Admission", "year_Of_Graduation", "status"];
      
    case "researchpapers":
      return ["faculty_name", "title", "publication_date", "journal_name", "co_authors"];
    case "conference": return ["faculty_Name", "conference_Name", "paper_Title", "presentation_Date", "conference_Type", "conference_Location", "conference_Mode", "publication_Status", "journal_Name", "issue_Number", "indexing", "certificatePdf"];

    case "awards":
      return [
        "faculty_Name",
        "award_Name",
        "awarded_By",
        "award_Date",
        "category",
        "recognition_Type",
        "event_Name",
        "description",
        "certificatePdf"
      ];


    case "developmentprogram": return ["faculty_Name", "program_Name", "organized_By", "start_Date", "end_Date", "programT_ype", "mode", "location", "duration_Days", "certificatePdf"];

    case "patents":
      return ["faculty_Name", "patent_Title", "patent_Number", "application_Date", "status", "inventor_Names", "patentType", "patent_Office", "grant_Date", "expiry_Date", "country", "patent_Category", "certificatePdf"];

    case "certificate":
      return ["student_name", "enrollment_number", "certificate_name", "certificate_type", "issued_by", "issue_date", "validity_period", "grade_or_score", "certificate_description", "mode_of_training", "related_course_or_program", "certificate_status", "verified", "certificatePdf"];
    case "hackathon":
      return ["student_Name", "enrollment_Number", "event_Name", "date", "team_Name", "team_Size", "mentor_Name", "hackathon_Type", "organizing_Body", "venue", "problem_Statement", "technology_Used", "prize_Money", "sponsoring_Company", "position", "project_GithubLink", "project_Description", "certificate_Status", "certificatePdf"];

    case "placement":
      return ["id", "student_name", "company_name", "job_role", "branch", "placement_type", "package", "joining_date", "offer_letter_pdf", "company_location", "interview_mode"];
    case "internship":
      return ["studentName", "enrollment_Number", "company_Name", "role", "internship_Type", "stipend", "duration", "department", "mentorName", "mentorEmail", "technologies_Used", "project_Name", "project_Description", "skills_Gained", "company_Location", "internship_Status", "start_Date", "end_Date", "offerLetter_Link", "experience_LetterLink", "certificatePdf"];

    case "researchpaper":
      return ["student_name", "title", "publication_date", "journal_name", "co_authors"];
    case "sports":
      return ["student_Name", "sport_Name", "achievement", "event_Date", "event_Name", "event_Level", "event_Location", "position", "certificate", "coachName"];

    default:
      return [];
  }
};



return (
  <div>
  <Navbar />
  <div className="p-6 bg-gray-100 text-black min-h-screen">
      <h2 className="text-2xl font-semibold mb-4">Admin Dashboard</h2>
      {/* Main Tabs */}
      <div className="flex space-x-4 mb-4">
        <Button
          className={`px - 4 py-2 text-base font-semibold rounded ${activeTab === "faculty" ? "bg-blue-500 text-white" : "bg-gray-200"}`}
        onClick={() => { setActiveTab("faculty");  }}
          >
        Faculty
      </Button>
      <Button
        className={`px - 4 py-2 text-base font-semibold rounded ${activeTab === "student" ? "bg-blue-500 text-white" : "bg-gray-200"}`}
      onClick={() => { setActiveTab("student"); setSubTab(null); }}
          >
      Student
      </Button>
  </div >

  {/* Sub-tabs for Faculty and Student */ }
{
  activeTab === "faculty" && (
    <div className="flex space-x-4 mb-4">
      {facultyTabs.map((tab) => (
        <button
          key={tab}
          className={`px-4 py-2 text-base font-semibold rounded ${subTab === tab ? "bg-green-500 text-white" : "bg-gray-200"}`}
          onClick={() => setSubTab(tab)}
            >
          {tab.charAt(0).toUpperCase() + tab.slice(1)}
        </button>
        ))
      }
        </div >
      )
  }

{
  activeTab === "student" && (
    <div className="flex space-x-4 mb-4">
      {studentTabs.map((tab) => (
        <button
          key={tab}
          className={`px-4 py-2 text-base font-semibold rounded ${subTab === tab ? "bg-green-500 text-white" : "bg-gray-200"}`}
      onClick={() => setSubTab(tab)}
            >
      {tab.charAt(0).toUpperCase() + tab.slice(1)}
    </button>
  ))
}
        </div >
      )}

{/* Display selected tab content */ }
<div>
  {subTab && <h2 className="text-xl font-bold">{subTab.charAt(0).toUpperCase() + subTab.slice(1)} Content</h2>}
</div>


{/* Form */ }
<form onSubmit={handleAddOrUpdate} className="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
  {getFormFields().map((field) => (
     field == "certificatePdf" ? 
    <input
      key={field}
      type="file"
      placeholder={field.split("_").join(" ").toUpperCase()}
      value={newEntry[field] ?? ""} // Ensures no undefined value
      onChange={(e) => setFile({ ...newEntry, [field]: e.target.files[0] })}
      className="border p-2 rounded"
      
    /> :
    <input
      key={field}
      type="text"
      placeholder={field.split("_").join(" ").toUpperCase()}
      value={newEntry[field] ?? ""} // Ensures no undefined value
      onChange={(e) => setNewEntry({ ...newEntry, [field]: e.target.value })}
      className="border p-2 rounded"
    />

  ))}
  <button type="submit" className="px-4 py-2 text-base font-semibold bg-[#00062B] text-white rounded">
    {"Add this "}
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
    </div>
  );
};
export default AdminDashboard;