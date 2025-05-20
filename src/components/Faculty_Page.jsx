import React, { useState, useEffect } from "react";
import * as XLSX from "xlsx";
import AIMLBG from "./img/AIML_IMG.webp";
import {
  fetchFacultyData,
  fetchFacultyawardsData,
  fetchFacultyconferencesData,
  fetchFacultyfdpsData,
  fetchFacultypatentsData,
  fetchFacultyresearchpapersData,
} from "../api";

const Faculty = () => {
  const [selectedCategory, setSelectedCategory] = useState(null);
  const [searchQuery, setSearchQuery] = useState("");
  const [facultyData, setFacultyData] = useState([]);
  const [filteredData, setFilteredData] = useState([]);
  const [selectedColumns, setSelectedColumns] = useState([]);
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");

  const tableHeaders = {
    Faculty: ["id", "name", "email", "department", "mobile_no", "teaching_experience","industrial_experience", "designation"],
    ResearchPaper: ["id", "faculty_name", "title", "publication_date", "journal_name", "co_authors"],
    Awards: ["id", "facultyName", "awardName", "awardedBy", "awardDate", "category", "recognitionType", "eventName", "description", "certificatePdf"]
    , Conference: ["id", "facultyName", "conferenceName", "paperTitle", "presentationDate", "conferenceType", "conferenceLocation", "conferenceMode", "publicationStatus", "journalName", "issnNumber", "indexing", "certificatePdf"]
    , DevelopmentProgram: ["id", "facultyName", "programName", "organizedBy", "startDate", "endDate", "programType", "mode", "location", "durationDays", "certificatePdf"],
    Patents: ["id", "facultyName", "patentTitle", "patentNumber", "applicationDate", "status", "inventorNames", "patentType", "patentOffice", "grantDate", "expiryDate", "country", "patentCategory", "certificatePdf"],
  };

  const downloadCertificate = async (url, fileName) => {
    try {
      const response = await fetch(url, { mode: "cors" });
      const blob = await response.blob();

      const link = document.createElement("a");
      link.href = URL.createObjectURL(blob);
      link.download = `${fileName.replace(/\s+/g, "_")}_Certificate.pdf`;
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);

      URL.revokeObjectURL(link.href);
    } catch (err) {
      console.error("Error downloading PDF:", err);
      alert("Failed to download certificate.");
    }
  };

  useEffect(() => {
    if (selectedCategory) {
      const getData = async () => {
        try {
          let data = [];
          switch (selectedCategory) {
            case "Faculty":
              data = await fetchFacultyData();
              break;
            case "Awards":
              data = await fetchFacultyawardsData();
              break;
            case "Conference":
              data = await fetchFacultyconferencesData();
              break;
            case "DevelopmentProgram":
              data = await fetchFacultyfdpsData();
              break;
            case "Patents":
              data = await fetchFacultypatentsData();
              break;
            case "ResearchPaper":
              data = await fetchFacultyresearchpapersData();
              break;
            default:
              break;
          }
          data = data.map((item) => {
            const updatedItem = { ...item };

            Object.entries(item).forEach(([key, value]) => {
              if (
                value &&
                typeof value === "string" &&
                !isNaN(Date.parse(value))
              ) {
                updatedItem[key] = new Date(value).toISOString().split("T")[0];
              }
            });

            return updatedItem;
          });



          setFacultyData(data);
          setFilteredData(data);
          setSelectedColumns(tableHeaders[selectedCategory]);
        } catch (error) {
          console.error("Error fetching data:", error);
        }
      };

      getData();
    }
  }, [selectedCategory]);

  const handleColumnSelection = (column) => {
    setSelectedColumns((prev) =>
      prev.includes(column)
        ? prev.filter((col) => col !== column)
        : [...prev, column]
    );
  };
  const applyFilters = () => {
    let filtered = facultyData.filter((item) =>
      Object.values(item).some((value) =>
        value?.toString().toLowerCase().includes(searchQuery.toLowerCase())
      )
    );

    if (startDate && endDate) {
      const start = new Date(startDate);
      const end = new Date(endDate);

      filtered = filtered.filter((item) => {
        const dateFields = [
          "awardDate",
          "publicationDate",
          "presentationDate",
          "applicationDate",
          "grantDate",
          "expiryDate",
          "startDate",
          "endDate",
          "grantDate",
          "expiryDate",
        ];

        return dateFields.some((field) => {
          if (item[field]) {
            const itemDate = new Date(item[field]);
            return itemDate >= start && itemDate <= end;
          }
          return false;
        });
      });
    }

    setFilteredData(filtered);
  };
  useEffect(() => {
    applyFilters();
  }, [searchQuery, startDate, endDate, facultyData]); // Ensure it runs whenever data or filters change


  const downloadExcel = () => {
    const cleanedData = filteredData.map((row) => {
      const newRow = {};
      selectedColumns.forEach((col) => {
        if (col.toLowerCase().includes("certificate")) {
          newRow[col] = row[col] ? "Download Link" : "";
        } else {
          newRow[col] = row[col];
        }
      });
      return newRow;
    });

    const worksheet = XLSX.utils.json_to_sheet(cleanedData);
    const workbook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(workbook, worksheet, "Faculty Data");
    XLSX.writeFile(workbook, "Faculty_Data.xlsx");
  };


  return (
    <div className="w-full h-full bg-white">
      <div className="w-full h-96 relative bg-[#030927]">
        <img
          className="absolute inset-0 w-full h-full object-cover opacity-30 z-0"
          src={AIMLBG}
          alt="Faculty Background"
        />
        <div className="relative z-10 flex items-center justify-center h-full">
          <h1 className="text-white text-4xl text-center -mt-16 font-semibold">
            Faculty Details & Achievements
          </h1>
        </div>
      </div>
      <div className="w-[80vw] mx-auto my-8 flex gap-4 items-center">
        <input
          type="text"
          placeholder="Search..."
          className="flex-1 p-3 border text-black font-semibold border-gray-400 rounded"
          value={searchQuery}
          onChange={(e) => {
            setSearchQuery(e.target.value);
            applyFilters(); // Apply filter whenever the search text changes
          }}
        />

        {Object.keys(tableHeaders).map((category) => (
          <button
            key={category}
            className="px-4 py-2 text-base font-semibold bg-[#00062B] text-white rounded"
            onClick={() => setSelectedCategory(category)}
          >
            {category}
          </button>
        ))}
      </div>
      {selectedCategory && (
        <div className="w-[80vw] mx-auto my-4 p-4 border border-gray-400 rounded bg-gray-200 text-black">
          <h3 className="text-lg font-bold mb-2">Select Columns to Display:</h3>
          <div className="grid grid-cols-3 gap-2">
            {tableHeaders[selectedCategory].map((col) => (
              <label key={col} className="flex items-center space-x-2">
                <input
                  type="checkbox"
                  checked={selectedColumns.includes(col)}
                  onChange={() => handleColumnSelection(col)}
                />
                <span>{col}</span>
              </label>
            ))}
          </div>
        </div>
      )}
      <div className="w-[80vw] mx-auto flex gap-4 items-center">
        <input
          type="date"
          value={startDate}
          onChange={(e) => {
            setStartDate(e.target.value);
            applyFilters();
          }}
          className="p-2 border text-black"
        />
        <input
          type="date"
          value={endDate}
          onChange={(e) => {
            setEndDate(e.target.value);
            applyFilters();
          }}
          className="p-2 border text-black"
        />

        <button
          onClick={applyFilters}
          className="px-4 py-2 bg-blue-500 text-white font-semibold rounded"
        >
          Apply Filters
        </button>
        <button
          onClick={downloadExcel}
          className="px-4 py-2 bg-green-500 text-white font-semibold rounded"
        >
          Download Excel
        </button>
      </div>
      <div className="w-[80vw] m-auto mt-8">
        <div className="w-[80vw] mx-auto mt-6 text-black font-bolder text-3xl">
          {selectedCategory} ({filteredData.length} results)
        </div>
        {selectedCategory && (
          <table className="w-full text-center mt-4 bg-white text-[#75161C] border-collapse">
            <thead>
              <tr>
                {selectedColumns.map((col) => (
                  <th key={col} className="px-4 py-2 border">
                    {col
                      .replace(/_/g, " ")                     // Replace underscores with spaces
                      .replace(/([a-z])([A-Z])/g, "$1 $2")    // Add space before capital letters
                      .toUpperCase()}
                  </th>
                ))}
              </tr>
            </thead>
            <tbody>
              {filteredData.map((row, index) => (
                <tr key={index}>
                  {selectedColumns.map((col) => (
                    <td key={col} className="px-4 py-2 border">
                      {col.toLowerCase().includes("certificate") && row[col] ? (
                        <button
                          onClick={() => downloadCertificate(row[col], row["facultyName"] || row["name"] || "Certificate")}
                          className="text-blue-600 underline"
                        >
                          Download PDF
                        </button>

                      ) : (
                        row[col]
                      )}
                    </td>
                  ))}
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
};

export default Faculty;