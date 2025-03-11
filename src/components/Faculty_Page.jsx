import React, { useState, useEffect } from "react";
import * as XLSX from "xlsx";
import AIMLBG from "./img/AIML_IMG.webp";
import { fetchFacultyData, fetchFacultyawardsData, fetchFacultyconferencesData, fetchFacultyfdpsData, fetchFacultypatentsData, fetchFacultyresearchpapersData } from "../api";

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

const Faculty = () => {
  const [selectedCategory, setSelectedCategory] = useState(null);
  const [searchQuery, setSearchQuery] = useState("");
  const [facultyData, setFacultyData] = useState([]);
  const [filter, setFilter] = useState("");
  const [filterValues, setFilterValues] = useState([]);
  const [selectedFilterValue, setSelectedFilterValue] = useState("");
  const [filteredData, setFilteredData] = useState([]);
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");


  const CategoriesDetails = {
    Faculty: [],
    ResearchPaper: [],
    Conference: [],
    Awards: [],
    DevelopmentProgram: [],
    Patents: [],
  };

  const tableHeaders = {
    Faculty: ["id", "email", "name", "department"],
    ResearchPaper: ["id", "faculty_name", "title", "publication_date", "journal_name", "co_authors"],
    Awards: ["id", "faculty_name", "award_name", "awarded_by", "award_date"],
    Conference: ["id", "faculty_name", "conference_name", "paper_title", "presentation_date"],
    DevelopmentProgram: ["id", "faculty_name", "program_name", "organized_by", "start_date", "end_date"],
    Patents: ["id", "faculty_name", "patent_title", "patent_number", "application_date", "status"]
  };
  useEffect(() => {
    if (selectedCategory) {
      const getData = async () => {
        try {
          let data = [];
          if (selectedCategory === "Faculty") {
            data = await fetchFacultyData();
          } else if (selectedCategory === "Awards") {
            data = await fetchFacultyawardsData();
          } else if (selectedCategory === "Conference") {
            data = await fetchFacultyconferencesData();
          } else if (selectedCategory === "DevelopmentProgram") {
            data = await fetchFacultyfdpsData();
          } else if (selectedCategory === "Patents") {
            data = await fetchFacultypatentsData();
          } else if (selectedCategory === "ResearchPaper") {
            data = await fetchFacultyresearchpapersData();
          }

          if (data) {
            setFacultyData(data);  // ✅ Set facultyData instead of filteredData
            setFilteredData(data); // Keep filteredData updated as well
          }
        } catch (error) {
          console.error("Error fetching data:", error);
        }
      };
      getData();
    }
  }, [selectedCategory]);



  useEffect(() => {
    if (filter && facultyData.length > 0) {
      const uniqueValues = [...new Set(facultyData.map(item => item[filter]))];
      setFilterValues(uniqueValues);
    } else {
      setFilterValues([]);
    }
  }, [filter, facultyData]);


  useEffect(() => {
    let filtered = facultyData;

    // 🔍 Text Search Filter
    if (searchQuery) {
      filtered = filtered.filter(item =>
        Object.values(item).some(value =>
          value.toString().toLowerCase().includes(searchQuery.toLowerCase())
        )
      );
    }

    // 🔍 Dropdown Filter
    if (selectedFilterValue) {
      filtered = filtered.filter(item => item[filter] === selectedFilterValue);
    }

    // 🔍 Date Range Filter (Applies only if both startDate and endDate are selected)
    if (startDate && endDate) {
      filtered = filtered.filter(item => {
        const itemDate = new Date(item["start_date"]); // Change "start_date" to your actual date column
        return itemDate >= new Date(startDate) && itemDate <= new Date(endDate);
      });
    }

    setFilteredData(filtered);
  }, [searchQuery, selectedFilterValue, startDate, endDate, facultyData]);



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
      {/* Background Section */}
      <div className="w-full h-96 relative bg-[#030927]">
        <img className="absolute inset-0 w-full h-full object-cover opacity-30 z-0" src={AIMLBG} alt="Faculty Background" />
        <div className="relative z-10 flex items-center justify-center h-full">
          <h1 className="text-white text-4xl text-center -mt-16 font-semibold">
            Faculty Details & Achievements
          </h1>
        </div>
      </div>

      {/* Filter Section */}
      {/* Filter Section */}
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


      {/* Show result count */}
      {selectedCategory && (
        <p className="text-lg font-semibold text-[#75161C] text-center">
          Results Found: {filteredData.length}
        </p>
      )}


      {/* Faculty Table Section */}
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
              {selectedCategory}
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

export default Faculty;