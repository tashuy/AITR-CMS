import React, { useState, useEffect } from "react";
import axios from "axios";
import { Button } from "@mui/material";



const fetchDataFromApi = async (endpoint, method = "GET", data = null) => {
  try {
    const adminUsername = localStorage.getItem("adminUsername");
    // const url = endpoint
    url = `http://localhost:8080${endpoint}`
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
const apiEndpoints = {
  // mou endpoint
};


const ConsultancyDashboard = () => {

  const [newEntry, setNewEntry] = useState([])

  // get the headers

  const getTableHeaders = () => {
    const tab = "Mou" ;

    switch (tab){
      case "Mou":
      return [
        "AGENCY NAME",
        "DATE",
        "DURATION",
        "DISCRIPTION",
        "FUNDING", 
        "PDF"

      ]

      default:
        return [];
    }

  };

// Get table row data
const getTableRowData = (item) => {
  const tab = subTab || activeTab;
  switch (tab) {
    case "mou":
      return [item.id, item.name, item.date, item.duration, item.discription, item.funding, item.pdf];
    default : 
      return []
  }
}

// Get form fields based on active tab
const getFormFields = () => {

      return [
        "AGENCY NAME",
        "DATE",
        "DURATION",
        "DISCRIPTION",
        "FUNDING", 
        "PDF"
      ];


};




return (
  <div className="p-6 bg-gray-100 text-black min-h-screen">
    {/* Main Tabs */}
    <div className="flex space-x-4 mb-4">
    </div >
{/* Display selected tab content */ }
<div>

</div>


{/* Form */ }
<form className="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
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
    {"Add this "}
  </button>
</form>

{/* Table */ }
{/* <table className="min-w-full bg-white">
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
</table> */}
</div >
  );

};
export default ConsultancyDashboard;