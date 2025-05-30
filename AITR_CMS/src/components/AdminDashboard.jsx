import React, { useState, useEffect } from "react";
import axios from "axios";
import { Button } from "@mui/material";



const fetchDataFromApi = async (endpoint, method = "GET", data = null) => {
  try {
    const adminUsername = localStorage.getItem("adminUsername");
    const url = "http://localhost:3000/student"
    // url = `http://localhost:8080${endpoint}`
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
      const endpoint = editing
        ? `${apiEndpoints[tabKey]}/${newEntry.id}`
        : apiEndpoints[tabKey];


      if (!endpoint) return;
      const res = await fetchDataFromApi(endpoint);
      setData(res.response);
      console.log("API Response:", res);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  useEffect(() => {
    fetchData();
  }, [activeTab, subTab]);
  const handleDownloadCertificate = async (id) => {
    try {
      const response = await axios.get(`http://localhost:8080/sports/${id}/certificate`, {
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
  try {
    const tabKey = subTab || activeTab;
    const endpoint = editing
      ? `${ apiEndpoints[tabKey]}/${newEntry.id}`
        : apiEndpoints[tabKey];
const method = editing ? "PUT" : "POST";

let formData = new FormData();

if (tabKey === "sports" && newEntry.certificateFile) {
  Object.entries(newEntry).forEach(([key, value]) => {
    if (value !== undefined && key !== "certificateFile") {
      formData.append(key, value);
    }
  });
  formData.append("certificate", newEntry.certificateFile);
}

const config = {
  method,
  url:` http://localhost:8080${endpoint}`,
  headers: {
    ...(tabKey === "sports"
      ? { "Content-Type": "multipart/form-data" }
      : { "Content-Type": "application/json" }),
  },
  data: tabKey === "sports" ? formData : newEntry,
};

await axios(config);
setNewEntry({});
setEditing(false);
fetchData(); // Refresh data
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
  return ["Id", "Faculty Name", "Conference Name", "Paper Title", "Presentation Date", "Conference Type", "Conference Location", "Conference Mode", "Publication Status", "Journal Name", "Issn Number", "Indexing", "Certificate Link", "Actions"];

case "awards":
  return ["Id", "Faculty Name", "Award Name", "Awarded By", "Award Date", "Category", "Recognition Type", "Event Name", "Description", "Certificate Link", "Actions"];

case "developmentprogram":
  return ["Id", "Faculty Name", "Program Name", "Organized By", "Start Date", "End Date", "Program Type", "Mode", "Location", "Duration Days", "Certificate Link", "Actions"];

case "patents":
  return ["Id", "Faculty Name", "Patent Title", "Patent Number", "Application Date", "Status", "Inventor Names", "Patent Type", "Patent Office", "Grant Date", "Expiry Date", "Country", "Patent Category", "Certificate Link", "Actions"];

case "certificate":
  return ["Id", "Student Name", "Enrollment Number", "Certificate Name", "Certificate Type", "Issued By", "Issue Date", "Validity Period", "Grade Or Score", "Certificate Description", "Mode Of Training", "Related Course Or Program", "Certificate Status", "Verified", "Certificate Link", "Actions"];

case "hackathon":
  return ["Id", "Student Name", "Enrollment Number", "Event Name", "Date", "Team Name", "Team Size", "Mentor Name", "Hackathon Type", "Organizing Body", "Venue", "Problem Statement", "Technology Used", "Prize Money", "Sponsoring Company", "Position", "Project Github Link", "Project Description", "Certificate Status", "Certificate Link", "Actions"];

case "placement":
  return ["Id", "Student Name", "Company Name", "Job Role", "Branch", "Placement Type", "Package", "Joining Date", "Offer Letter Pdf", "Company Location", "Interview Mode", "Actions"];

case "internship":
  return ["Id", "Student Name", "Enrollment Number", "Company Name", "Role", "Internship Type", "Stipend", "Duration", "Department", "Mentor Name", "Mentor Email", "Technologies Used", "Project Name", "Project Description", "Skills Gained", "Company Location", "Internship Status", "Start Date", "End Date", "Offer Letter Link", "Experience Letter Link", "Certificate Link", "Actions"];

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
        return ["name", "email", "year", "course", "branch", "cgpa", "dateOfBirth", "gender", "yearOfAdmission", "yearOfGraduation", "status"];
      
    case "researchpapers":
      return ["faculty_name", "title", "publication_date", "journal_name", "co_authors"];
    case "conference": return ["facultyName", "conferenceName", "paperTitle", "presentationDate", "conferenceType", "conferenceLocation", "conferenceMode", "publicationStatus", "journalName", "issnNumber", "indexing", "certificatePdf"];

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
        "certificatePdf"
      ];


    case "developmentprogram": return ["facultyName", "programName", "organizedBy", "startDate", "endDate", "programType", "mode", "location", "durationDays", "certificateLink"];

    case "patents":
      return ["facultyName", "patentTitle", "patentNumber", "applicationDate", "status", "inventorNames", "patentType", "patentOffice", "grantDate", "expiryDate", "country", "patentCategory", "certificatePdf"];

    case "certificate":
      return ["student_name", "enrollment_number", "certificate_name", "certificate_type", "issued_by", "issue_date", "validity_period", "grade_or_score", "certificate_description", "mode_of_training", "related_course_or_program", "certificate_status", "verified", "certificatePdf"];
    case "hackathon":
      return ["studentName", "enrollmentNumber", "eventName", "date", "teamName", "teamSize", "mentorName", "hackathonType", "organizingBody", "venue", "problemStatement", "technologyUsed", "prizeMoney", "sponsoringCompany", "position", "projectGithubLink", "projectDescription", "certificateStatus", "certificatePdf"];

    case "placement":
      return ["id", "student_name", "company_name", "job_role", "branch", "placement_type", "package", "joining_date", "offer_letter_pdf", "company_location", "interview_mode"];
    case "internship":
      return ["studentName", "enrollmentNumber", "companyName", "role", "internshipType", "stipend", "duration", "department", "mentorName", "mentorEmail", "technologiesUsed", "projectName", "projectDescription", "skillsGained", "companyLocation", "internshipStatus", "startDate", "endDate", "offerLetterLink", "experienceLetterLink", "certificatePdf"];

    case "researchpaper":
      return ["student_name", "title", "publication_date", "journal_name", "co_authors"];
    case "sports":
      return ["studentName", "sportName", "achievement", "eventDate", "eventName", "eventLevel", "eventLocation", "position", "certificate", "coachName"];

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
        className={`px - 4 py-2 text-base font-semibold rounded ${activeTab === "faculty" ? "bg-blue-500 text-white" : "bg-gray-200"}`}
      onClick={() => { setActiveTab("faculty"); setSubTab(null); }}
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
        <Button
          key={tab}
          className={`px - 4 py-2 text-base font-semibold rounded ${subTab === tab ? "bg-green-500 text-white" : "bg-gray-200"}`}
      onClick={() => setSubTab(tab)}
            >
      {tab.charAt(0).toUpperCase() + tab.slice(1)}
    </Button>
  ))
}
        </div >
      )}

{
  activeTab === "student" && (
    <div className="flex space-x-4 mb-4">
      {studentTabs.map((tab) => (
        <Button
          key={tab}
          className={`px - 4 py-2 text-base font-semibold rounded ${subTab === tab ? "bg-green-500 text-white" : "bg-gray-200"}`}
      onClick={() => setSubTab(tab)}
            >
      {tab.charAt(0).toUpperCase() + tab.slice(1)}
    </Button>
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