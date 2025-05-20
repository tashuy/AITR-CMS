// Assuming you have already added the following API functions somewhere:
// import {
//   fetchMouCollabData,
//   fetchMouEventData,
//   fetchMouOthersData,
//   fetchAchievementData,
// } from "../api/mouApis";

import React, { useState, useEffect } from "react";
import AIMLBG from "./img/AIML_IMG.webp";


const Institute = () => {
  const [showSubTabs, setShowSubTabs] = useState(false);
  const [activeSubTab, setActiveSubTab] = useState("Collab");
  const [tableData, setTableData] = useState([]);
  const [loading, setLoading] = useState(false);

  const subTabs = ["Collab", "Achievement", "Event", "Others"];

  useEffect(() => {
    if (showSubTabs) fetchData(activeSubTab);
  }, [activeSubTab, showSubTabs]);

  const fetchData = async (tab) => {
    setLoading(true);
    try {
      let data = [];
      if (tab === "Collab") data = await fetchMouCollabData();
      else if (tab === "Achievement") data = await fetchAchievementData();
      else if (tab === "Event") data = await fetchMouEventData();
      else if (tab === "Others") data = await fetchMouOthersData();
      setTableData(data);
    } catch (error) {
      console.error("Error fetching data:", error);
      setTableData([]);
    } finally {
      setLoading(false);
    }
  };
  const tableHeaders = {
    Collab: [
      "id",
      "instituteName",
      "collabType",
      "startDate",
      "endDate",
      "objective",
      "activitiesConducted",
      "outcomes",
      "documentProofPdf"
    ],
    Achievement: [
      "id",
      "instituteName",
      "achievementTitle",
      "achievementDate",
      "achievementLevel",
      "organizedBy",
      "category",
      "description",
      "certificatePdf"
    ],
    Event: [
      "id",
      "eventName",
      "eventType",
      "startDate",
      "endDate",
      "location",
      "description",
      "organizer",
      "participantsCount",
      "eventReportPdf"
    ],
    Others: [
      "id",
      "activityName",
      "instituteName",
      "activityType",
      "date",
      "description",
      "remarks",
      "supportingDocPdf"
    ]
  };
  
  return (
    <div className="w-full h-full bg-white">
      {/* Header */}
      <div className="w-full h-96 relative bg-[#030927]">
        <img
          className="absolute inset-0 w-full h-full object-cover opacity-30 z-0"
          src={AIMLBG}
          alt="AIML Background"
        />
        <div className="relative z-10 flex items-center justify-center h-full">
          <h1 className="text-white text-4xl text-center -mt-16 font-semibold uppercase">
            aitr
          </h1>
        </div>
      </div>

      {/* MOU Button */}
      <div className="flex justify-center gap-2 py-6">
        <button
          onClick={() => setShowSubTabs(true)}
          className="px-6 py-2 bg-[#00062B] text-white font-semibold rounded"
        >
          MOU
        </button>
      </div>

      {/* Filters and Sub-tabs */}
      {showSubTabs && (
        <>
          {/* Filters */}
          <div className="w-[80vw] mx-auto my-8 flex flex-wrap gap-4 items-center">
            <input
              type="text"
              placeholder="Search..."
              className="flex-1 p-3 border text-black font-semibold border-gray-400 rounded"
            />

            <select className="p-3 border text-black font-semibold border-gray-400 rounded">
              <option value="">Filter by</option>
            </select>

            <select className="p-3 border text-black font-semibold border-gray-400 rounded">
              <option value="">Select Value</option>
            </select>

            <div className="flex gap-4 items-center">
              <label className="font-semibold text-black">Start Date:</label>
              <input
                type="date"
                className="p-2 border text-black font-semibold border-gray-400 rounded"
              />

              <input
                type="date"
                className="p-2 border text-black font-semibold border-gray-400 rounded"
              />

              <button className="px-4 py-2 bg-red-600 text-white font-semibold rounded">
                Clear data Filter
              </button>
            </div>
          </div>

          {/* Sub-tabs */}
          <div className="flex justify-center gap-4 py-4">
            {subTabs.map((tab, index) => (
              <button
                key={index}
                onClick={() => setActiveSubTab(tab)}
                className={`px-4 py-2 rounded font-semibold ${
                  activeSubTab === tab
                    ? "bg-blue-700 text-white"
                    : "bg-gray-300 text-black"
                }`}
              >
                {tab}
              </button>
            ))}
          </div>

          {/* Column Selection */}
          <div className="w-[80vw] m-auto mt-4 p-4 bg-gray-200 text-black rounded">
            <h3 className="font-bold text-lg mb-2">Select Columns to Display:</h3>
            <div className="flex flex-wrap gap-4">
              {Array.from({ length: 5 }, (_, i) => (
                <label key={i} className="flex items-center space-x-2">
                  <input type="checkbox" />
                  <span>Column {i + 1}</span>
                </label>
              ))}
            </div>
          </div>

          {/* Data Table Section */}
          <div className="mt-10 w-[80vw] mx-auto">
            <h2 className="text-4xl font-extrabold text-[#00062B] mb-6">
              {activeSubTab} ({tableData.length} results)
            </h2>

            {loading ? (
              <p className="text-gray-500">Loading...</p>
            ) : (
              tableData.length > 0 ? (
                <table className="w-full table-auto border border-gray-400">
                  <thead>
                    <tr className="bg-gray-300">
                      {Object.keys(tableData[0]).map((key) => (
                        <th key={key} className="border p-2 text-left capitalize">
                          {key.replace(/([A-Z])/g, ' $1')}
                        </th>
                      ))}
                    </tr>
                  </thead>
                  <tbody>
                    {tableData.map((item, index) => (
                      <tr key={index} className="border-t">
                        {Object.values(item).map((val, idx) => (
                          <td key={idx} className="border p-2">{val}</td>
                        ))}
                      </tr>
                    ))}
                  </tbody>
                </table>
              ) : (
                <p className="text-gray-500">No data available.</p>
              )
            )}

            <button className="mt-4 px-6 py-2 bg-green-600 text-white font-semibold rounded">
              Download Data
            </button>
          </div>
        </>
      )}
    </div>
  );
};

export default Institute;