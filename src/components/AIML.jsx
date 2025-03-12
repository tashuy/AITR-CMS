import React, { useState, useEffect } from "react";
import AIMLBG from "./img/AIML_IMG.webp";
import * as XLSX from "xlsx";
import { fetchStudentData, fetchCertificatestData, fetchHackathonsData, fetchInternshipsData, fetchResearchpapersData, fetchSportsData, fetchstudentplacementsData } from "../api";

const Table = ({ columns, data }) => {
  if (!data || data.length === 0) {
    return <p className="text-gray-500">No data available.</p>;
  }
  return (
    <table className="w-full text-center mt-4 bg-white text-[#75161C] border-collapse">
      <thead>
        <tr className="border-b-2 border-l-2 border-[#75161C] text-black">
          {columns.map((col, index) => (
            <th key={index} className="px-4 py-2 border-r-2 border-t-2 border-[#75161C]">
              {col}
            </th>
          ))}
        </tr>
      </thead>
      <tbody>
        {data.map((row, rowIndex) => (
          <tr key={rowIndex} className="bg-white border-b-2 border-l-2 border-[#75161C]">
            {columns.map((col, cellIndex) => (
              <td key={cellIndex} className="px-4 py-2 border-r-2 border-[#75161C]">
                {row[col]}
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
    Students: ["email", "name", "year", "cgpa", "branch", "course"], // Removed "id"
    Certificate: ["student_name", "certificate_name", "issued_by", "issue_date", "certificate_link"],
    Hackathon: ["student_name", "event_name", "date", "position", "certificate_link"],
    Placement: ["student_name", "company_name", "joining_date", "offer_letter_link", "package"],
    Internship: ["student_name", "company_name", "role", "start_date", "end_date", "certificate_link"],
    ResearchPaper: ["student_name", "title", "publication_date", "journal_name", "co_authors"],
    Sports: ["student_name", "sport_name", "achievement", "event_date"],
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
            const uniqueValues = [...new Set(data.map(item => item[filter]))].filter(v => v !== undefined);
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
    let filtered = studentData.filter(item =>
      Object.values(item).some(value =>
        value.toString().toLowerCase().includes(searchQuery.toLowerCase())
      )
    );

    if (startDate && endDate) {
      filtered = filtered.filter(item => {
        const dateFields = ["issue_date", "date", "joining_date", "start_date", "publication_date", "event_date"];
        return dateFields.some(field => {
          if (item[field]) {
            const itemDate = new Date(item[field]);
            return itemDate >= new Date(startDate) && itemDate <= new Date(endDate);
          }
          return false;
        });
      });
    }


    setFilteredData(filtered);
  }, [searchQuery, filterValues, startDate, endDate, studentData]);


  const handleDownload = () => {
    if (filteredData.length === 0) {
      alert("No data available to download.");
      return;
    }
    const worksheet = XLSX.utils.json_to_sheet(filteredData);
    const workbook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(workbook, worksheet, selectedCategory);
    XLSX.writeFile(workbook, `${selectedCategory}_Data.xlsx`);
  };

  return (
    <div className="w-full h-full bg-white">
      <div className="w-full h-96 relative bg-[#030927]">
        <img className="absolute inset-0 w-full h-full object-cover opacity-30 z-0" src={AIMLBG} alt="AIML Background" />
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
              .filter(header => header !== "id")
              .map((header, index) => (
                <option key={index} value={header}>{header}</option>
              ))}
          </select>
        )}

        {filter && (
          <select
            className="p-3 border text-black font-semibold border-gray-400 rounded"
            value={selectedFilterValue}
            onChange={(e) => setSelectedFilterValue(e.target.value)}
          >
            <option value="">Select Value</option>
            {filterValues.map((value, index) => (
              <option key={index} value={value}>{value}</option>
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

            <label className="font-semibold text-black">End Date:</label>
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
            <button
              key={index}
              className="px-4 py-2 text-base font-semibold bg-[#00062B] text-white rounded"
              onClick={() => setSelectedCategory(category)}
            >
              {category}
            </button>
          ))}
        </div>

        {selectedCategory && (
          <div className="mt-10">
            <h2 className="text-4xl font-extrabold text-[#00062B] mb-6">
              {selectedCategory} ({filteredData.length} results)
            </h2>
            <Table columns={tableHeaders[selectedCategory]} data={filteredData} />
            <button onClick={handleDownload} className="mt-4 px-6 py-2 bg-green-600 text-white font-semibold rounded">
              Download {selectedCategory} Data
            </button>
          </div>
        )}
      </div>
    </div>
  );
};

export default AIML;
