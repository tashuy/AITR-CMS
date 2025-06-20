import React, { useState, useEffect } from "react";
import AIMLBG from "../assets/img/AIML_IMG.webp";
import * as XLSX from "xlsx";
import {
  fetchStudentData,
  fetchCertificatestData,
  fetchHackathonsData,
  fetchInternshipsData,
  fetchResearchpapersData,
  fetchSportsData,
  fetchstudentplacementsData,
} from "../api";
import { ButtonElement } from "./ui/ButtonElement";

const Table = ({ columns, data, selectedRows, setSelectedRows }) => {
  if (!data || data.length === 0) {
    return <p className="text-gray-500">No data available.</p>;
  }

  const toggleRowSelection = (row) => {
    setSelectedRows((prev) =>
      prev.includes(row) ? prev.filter((r) => r !== row) : [...prev, row]
    );
  };

  return (
    <table className="w-full text-center mt-4 bg-white text-[#75161C] border-collapse">
      <thead>
        <tr className="border-b-2 border-l-2 border-[#75161C] text-black">
          <th className="px-4 py-2 border-r-2 border-t-2 border-[#75161C]">
            Select
          </th>
          {columns.map((col, index) => (
            <th
              key={index}
              className="px-4 py-2 border-r-2 border-t-2 border-[#75161C]"
            >
              {col}
            </th>
          ))}
        </tr>
      </thead>
      <tbody>
        {data.map((row, rowIndex) => (
          <tr
            key={rowIndex}
            className="bg-white border-b-2 border-l-2 border-[#75161C]"
          >
            <td className="px-4 py-2 border-r-2 border-[#75161C]">
              <input
                type="checkbox"
                checked={selectedRows.includes(row)}
                onChange={() => toggleRowSelection(row)}
              />
            </td>
            {columns.map((col, cellIndex) => (
              <td
                key={cellIndex}
                className="px-4 py-2 border-r-2 border-[#75161C]"
              >
                {col.toLowerCase().includes("pdf") && row[col] ? (
                  <a
                    href={row[col]}
                    target="_blank"
                    rel="noopener noreferrer"
                    download={`Certificate_${row["studentName"] || "file"}.pdf`}
                    className="text-blue-500 underline"
                  >
                    Download PDF
                  </a>
                ) : (
                  row[col]
                )}
              </td>
            ))}

          </tr>
        ))}
      </tbody>
    </table>

  );
};
const AIML = () => {
  const [selectedCategory, setSelectedCategory] = useState(null);
  const [searchQuery, setSearchQuery] = useState("");
  const [studentData, setStudentData] = useState([]);
  const [filter, setFilter] = useState("");
  const [filterValues, setFilterValues] = useState("");
  const [uniqueFilterValues, setUniqueFilterValues] = useState([]);
  const [filteredData, setFilteredData] = useState([]);
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [selectedRows, setSelectedRows] = useState([]); // Store selected rows
  const [selectedColumns, setSelectedColumns] = useState([]); // Store selected columns
  const CategoriesDetails = {
    Students: [],
    Certificate: [],
    Hackathon: [],
    Placement: [],
    Internship: [],
    ResearchPaper: [],
    Sports: [],
  };

  const tableHeaders = {
    Students: [
      "id",
      "name",
      "email",
      "year",
      "course",
      "branch",
      "cgpa",
      "dateOfBirth",
      "gender",
      "yearOfAdmission",
      "yearOfGraduation",
      "status"
    ],
    Certificate: [
      "id",
      "studentName",
      "enrollmentNumber",
      "certificateName",
      "certificateType",
      "issuedBy",
      "issueDate",
      "validityPeriod",
      "gradeOrScore",
      "certificateDescription",
      "modeOfTraining",
      "relatedCourseOrProgram",
      "certificateStatus",
      "verified",
      "certificatePdf"
    ],

    Hackathon: [
      "id",
      "studentName",
      "enrollmentNumber",
      "eventName",
      "date",
      "teamName",
      "teamSize",
      "mentorName",
      "hackathonType",
      "organizingBody",
      "venue",
      "problemStatement",
      "technologyUsed",
      "prizeMoney",
      "sponsoringCompany",
      "position",
      "projectGithubLink",
      "projectDescription",
      "certificateStatus",
      "certificatePdf"
    ],

    Placement: ["id", "studentName", "companyName", "jobRole", "branch", "placementType", "packageAmount", "joiningDate", "offerLetterPdf", "companyLocation", "interviewMode"],

    Internship: [
      "id",
      "studentName",
      "enrollmentNumber",
      "companyName",
      "role",
      "internshipType",
      "stipend",
      "duration",
      "department",
      "mentorName",
      "mentorEmail",
      "technologiesUsed",
      "projectName",
      "projectDescription",
      "skillsGained",
      "companyLocation",
      "internshipStatus",
      "startDate",
      "endDate",
      
      "certificatePdf"
    ],

    ResearchPaper: [
      "student_name",
      "title",
      "publication_date",
      "journal_name",
      "co_authors",
    ],
    Sports: ["id", "studentName", "sportName", "achievement", "eventDate", "eventName", "eventLevel", "eventLocation", "position", "certificatePdf", "coachName"],

  };

  useEffect(() => {
    const getData = async () => {
      let data = [];
      try {
        switch (selectedCategory) {
          case "Students":
            data = await fetchStudentData();
            break;
          case "Certificate":
            data = await fetchCertificatestData();
            break;
          case "Hackathon":
            data = await fetchHackathonsData();
            break;
          case "Internship":
            data = await fetchInternshipsData();
            break;
          case "ResearchPaper":
            data = await fetchResearchpapersData();
            break;
          case "Placement":
            data = await fetchstudentplacementsData();
            break;
          case "Sports":
            data = await fetchSportsData();
            break;
          default:
            break;
        }


        if (data) {
          setStudentData(data);
          setFilteredData(data);
          if (filter) {
            const uniqueValues = [
              ...new Set(data.map((item) => item[filter])),
            ].filter((v) => v !== undefined);
            setUniqueFilterValues(uniqueValues);
          }
        }
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    if (selectedCategory) {
      getData();
    }
  }, [selectedCategory, filter]);
  useEffect(() => {
    if (filter) {
      const uniqueValues = [
        ...new Set(studentData.map((item) => item[filter])),
      ].filter((v) => v !== undefined);
      setUniqueFilterValues(uniqueValues);
    }
  }, [filter, studentData]);

  useEffect(() => {
    let filtered = studentData;

    // Apply Search Filter
    if (searchQuery) {
      filtered = filtered.filter((item) =>
        Object.values(item).some(
          (value) =>
            value &&
            value.toString().toLowerCase().includes(searchQuery.toLowerCase())
        )
      );
    }

    // Apply Filter Dropdown
    if (filter && filterValues) {
      filtered = filtered.filter((item) => item[filter] === filterValues);
    }

    // Apply Date Range Filter
    if (startDate && endDate) {
      const start = new Date(startDate);
      const end = new Date(endDate);

      filtered = filtered.filter((item) => {
        const dateFields = [
          "yearOfAdmission",
          "yearOfGraduation",
          "dateOfBirth",
          "issueDate",
          "date",                 // From Hackathon
          "joiningDate",
          "startDate",
          "endDate",
          "publication_date",
          "eventDate"
        ];


        return dateFields.some((field) => {
          if (item[field]) {
            const itemDate = new Date(item[field]);

            // Ensure itemDate is a valid date
            if (!isNaN(itemDate)) {
              return itemDate >= start && itemDate <= end;
            }
          }
          return false;
        });
      });
    }



    setFilteredData(filtered);
  }, [searchQuery, filter, filterValues, startDate, endDate, studentData]);

  const handleDownload = () => {
    if (selectedRows.length === 0) {
      alert("Please select at least one row to download.");
      return;
    }

    if (selectedColumns.length === 0) {
      alert("Please select at least one column to download.");
      return;
    }

    // Extract only selected columns
    const dataToDownload = selectedRows.map((row) =>
      Object.fromEntries(selectedColumns.map((col) => [col, row[col]]))
    );
    const worksheet = XLSX.utils.json_to_sheet(dataToDownload);
    const workbook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(workbook, worksheet, selectedCategory);
    XLSX.writeFile(workbook, `${selectedCategory}_SelectedData.xlsx`);


  };

  return (
    <div className="w-full h-full bg-white">
      <div className="w-full h-96 relative bg-[#030927]">
        <img
          className="absolute inset-0 w-full h-full object-cover opacity-30 z-0"
          src={AIMLBG}
          alt="AIML Background"
        />
        <div className="relative z-10 flex items-center justify-center h-full">
          <h1 className="text-white text-4xl text-center -mt-16 font-semibold">
            Artificial Intelligence & Machine Learning Department
          </h1>
        </div>
      </div>
      <div className="w-[80vw] mx-auto my-8 flex gap-4 items-center">
        <input
          type="text"
          placeholder="Search..."
          className="flex-1 p-3 border text-black font-semibold border-gray-400 rounded"
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
        />

        {selectedCategory && (
          <select
            className="p-3 border text-black font-semibold border-gray-400 rounded"
            value={filter}
            onChange={(e) => setFilter(e.target.value)}
          >
            <option value="">Filter by</option>
            {tableHeaders[selectedCategory]
              .filter((header) => header !== "id")
              .map((header, index) => (
                <option key={index} value={header}>
                  {header}
                </option>
              ))}
          </select>
        )}

        {filter && (
          <select
            className="p-3 border text-black font-semibold border-gray-400 rounded"
            value={filterValues}
            onChange={(e) => setFilterValues(e.target.value)}
          >
            <option value="">Select Value</option>
            {uniqueFilterValues.map((value, index) => (
              <option key={index} value={value}>
                {value}
              </option>
            ))}
          </select>
        )}

        {/* Date Range Filter */}
        {selectedCategory && (
          <div className="flex gap-4 items-center">
            <label className="font-semibold text-black">Start Date:</label>
            <input
              type="date"
              className="p-2 border text-black font-semibold border-gray-400 rounded"
              value={startDate}
              onChange={(e) => setStartDate(e.target.value)}
            />

            <input
              type="date"
              className="p-2 border text-black font-semibold border-gray-400 rounded"
              value={endDate}
              onChange={(e) => setEndDate(e.target.value)}
            />

            <button
              className="px-4 py-2 bg-red-600 text-white font-semibold rounded"
              onClick={() => {
                setStartDate("");
                setEndDate("");
              }}
            >
              Clear data Filter
            </button>
          </div>
        )}
      </div>

      <div className="w-[80vw] m-auto mt-8">
        <div className="flex justify-center gap-2 py-2">
          {Object.keys(CategoriesDetails).map((category, index) => (
            <ButtonElement
              key={index}
              onClick={() => setSelectedCategory(category)}
              children={category}
            />
          ))}
        </div>
        {selectedCategory && (
          <div className="mt-4 p-4 bg-gray-200 text-black rounded">
            <h3 className="font-bold text-lg mb-2">
              Select Columns to Display:
            </h3>
            <div className="flex flex-wrap gap-4">
              {tableHeaders[selectedCategory].map((col, index) => (
                <label key={index} className="flex items-center space-x-2">
                  <input
                    type="checkbox"
                    checked={selectedColumns.includes(col)}
                    onChange={() => {
                      setSelectedColumns((prev) =>
                        prev.includes(col)
                          ? prev.filter((c) => c !== col)
                          : [...prev, col]
                      );
                    }}
                  />
                  <span>
                    {col
                      .replace(/_/g, " ")                     // Replace underscores with spaces
                      .replace(/([a-z])([A-Z])/g, "$1 $2")    // Add space before capital letters
                      .toUpperCase()}
                  </span>
                </label>
              ))}

            </div>
          </div>
        )}

        {selectedCategory && (
          <div className="mt-10">
            <h2 className="text-4xl font-extrabold text-[#00062B] mb-6">
              {selectedCategory} ({filteredData.length} results)
            </h2>
            <Table
              columns={
                selectedColumns.length > 0
                  ? selectedColumns
                  : tableHeaders[selectedCategory]
              }
              data={filteredData}
              selectedRows={selectedRows}
              setSelectedRows={setSelectedRows}
            />

            <button
              onClick={handleDownload}
              className="mt-4 px-6 py-2 bg-green-600 text-white font-semibold rounded"
            >
              Download {selectedCategory} Data
            </button>
          </div>
        )}
      </div>
    </div>
  );

};

export default AIML;