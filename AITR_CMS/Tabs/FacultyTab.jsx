import { useEffect, useRef, useState } from "react";
import DataTable, { Direction, Media } from 'react-data-table-component';
import DownloadComponent from "../src/components/DownlaodComponent";
// import { getfieldData, postfieldData } from "../src/components/fieldData";
import { API_BASE_URL, apiEndpoints } from "../src/config";
import axios from "axios";
import department from "../src/components/Department";



function FacultyTabs(){
    
    const [data , setData ] = useState([])
    const [subTab, setSubTab ] = useState(localStorage.getItem('currentTab'))
    const [entry , setEntry] = useState([]);
    const [file , setFile ] = useState(null);

    const previousSubTab = useRef('');

    const customStyles = {
      rows: {
        style: {
          display:"flex",
          justifyContent:"center",
          overflow : "auto",
          borderBottomWidth: "2px",
          fontSize:"20px",
          minHeight: '72px', // override the row height
        },
      },
      headCells: {
        style: {
          
          overflow: "hidden",
          textOverflow: "clip",
          whiteSpace: "nowrap",
          fontSize:"20px",
          color:"white",
          backgroundColor:"#4287f5",
          paddingLeft: '8px', // override the cell padding for head cells
          paddingRight: '8px',
        },
      },
      cells: {
        style: {
          
          // display:"flex",
          paddingLeft: '8px', // override the cell padding for data cells
          paddingRight: '8px',
        },
      },
    };

    const facultyTabs = [ "faculty", "researchpaper", "conference", "awards", "developmentprogram", "patents"];


    const getFormFields = () => {

        if(subTab == "faculty"){
          return ["name", "email", "department", "mobile_no", "years_Of_Experience", "designation"];
        }
        else if(subTab === "researchpaper"){
            return ["faculty_name", "title", "publication_date", "journal_name", "co_authors"];
        }
        else if (subTab === "conference"){
            return ["faculty_Name", "conference_Name", "paper_Title", "presentation_Date", "conference_Type", "conference_Location", "conference_Mode", "publication_Status", "journal_Name", "issue_Number", "indexing", "certificate_Pdf"];
        }
        else if(subTab == "awards"){
          return ["faculty_Name","award_Name","awarded_By","award_Date","category","recognition_Type","event_Name","description","certificate_Pdf"];
        }
        else if(subTab == "developmentprogram" ){
          return [ "faculty_Name", "program_Name", "organized_By", "start_Date", "end_Date", "programT_ype", "mode", "location", "duration_Days", "certificate_Link"];
        }
        else if(subTab == "patents"){
          return ["faculty_Name", "patent_Title", "patent_Number", "application_Date", "status", "inventor_Names", "patentType", "patent_Office", "grant_Date", "expiry_Date", "country", "patent_Category", "certificate_Pdf"];
        }
        else{
            return [];
        }
        
    }

    const getTableHeaders = () => {

      // todo : change name add _ in place of space
      // todo: get the data by calling the function
      // todo: add file uploading in input tab;
      // todo: add file dowloading functionality -. done
      // todo : add export csv from the library

      if(subTab == "faculty"){
          return ["name", "email", "department", "mobile_no", "years_Of_Experience", "designation"];
        }
      else if(subTab == "researchpaper"){
        return ["Id", "Faculty_Name", "Title", "Publication_Date", "Journal_Name", "Co_Authors", "Actions",];
      }
      else if(subTab == "conference"){
        return ["Id", "Faculty Name", "Conference Name", "Paper Title", "Presentation Date", "Conference Type", "Conference Location", "Conference Mode", "Publication Status", "Journal Name", "Issn Number", "Indexing", "Certificate_Link", "Actions"];
      }
      else if(subTab == "awards"){
      return ["Id", "Faculty Name", "Award Name", "Awarded By", "Award Date", "Category", "Recognition Type", "Event Name", "Description", "Certificate_Link", "Actions"];

      }     
      else if(subTab == "developmentprogram"){
        return ["Id", "Faculty Name", "Program Name", "Organized By", "Start Date", "End Date", "Program Type", "Mode", "Location", "Duration Days", "Certificate_Link", "Actions"];
      }
      else if(subTab == "patents"){
        return ["Id", "Faculty Name", "Patent Title", "Patent Number", "Application Date", "Status", "Inventor Names", "Patent Type", "Patent Office", "Grant Date", "Expiry Date", "Country", "Patent Category", "Certificate_Link", "Actions"];
      }
      else{
        return [];
      }
    }


    // creating the Headers and ploatting data using React-table
    const headers = getTableHeaders(subTab);
    const column = headers.map((header) => ( 
      ( header == "Certificate_Link" ? {
        name: header,
        button: true,
        cell: () => ( <DownloadComponent url={url} filename={filename} />),
      } : {
      name: header.split("_").join(""),
      selector: row => row[header.toLowerCase()],    
      } )
  ) )


  const handleAdd = async () => {
    try {
        const tabKey = subTab;
        const endpoint = `${apiEndpoints[tabKey]}`;
        const method = 'POST'    

        // file uploading schema
        const formData = new FormData();
        formData.append("entry" , entry)
        formData.append("certificate_file", file)

        // const config = {
        //   method,
        //   url:`http://localhost:8080/${endpoint}`,
        //   headers: {
        //     "Content-Type": "multipart/form-data" 
        //   },
        //   body: formData
        // };

        
        // const url = `${API_BASE_URL}/${endpoint}`
        const url = `http://localhost:8080/faculty`


        //  i am setting a custom example
        // url  =  faculty
          
        await axios({
        method: "post",
        url: url, 
        data : formData,
        headers: {
          "Content-Type": "multipart/form-data" 
        },
        });


        setEntry(null);

      
        // Reset state and refresh data

    } catch (error) {
        console.error("Error adding/updating entry:", error?.response?.data || error.message);
    }
  };

  async function fetchData(){
    const url = `${API_BASE_URL}/${subTab}`
    // const url = "http://localhost:8080/faculty"
    await axios.get(url).then((response) => setData(response.data.response))
  }

    useEffect(() => {
        previousSubTab.current = subTab
        localStorage.setItem('currentTab' , previousSubTab.current)
        fetchData()  
        // setdata(getfieldData(subTab))  
    }, [subTab])



    return <>
    <div className="flex space-x-4 mb-4">
      {facultyTabs.map((tab) => (
        <button onClick={() => setSubTab(tab)}
          key={tab}
          className={`px-4 py-2 text-base font-semibold rounded  ${subTab == tab ? "bg-blue-600 text-white": ""}`}
            >
          {tab.charAt(0).toUpperCase() + tab.slice(1)}
        </button>
        ))
      }
    </div >

    {/* Form fildes */}
    <form className="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
    {getFormFields().map((field) => (
      ( field == "certificate_Pdf" ? 
      <input 
        key={field}
        type="file"
        onChange={(e) => setFile(e.target.files[0]) }
        className="border p-2 rounded" /> :
      <input
        key={field}
        type="text"
        placeholder={field.split("_").join(" ").toUpperCase()}
        value={entry[field] ?? ""} // Ensures no undefined value
        onChange={(e) => setEntry({ ...entry, [field]: e.target.value })}
        className="border p-2 rounded"
      /> )

    ))}
 
    <button onClick={handleAdd} className="px-4 py-2 text-base font-semibold bg-[#00062B] text-white rounded">
    {"Add this "}
    </button>
    </form>
    <DataTable columns={column} data={data} customStyles={customStyles}  />

    
    </>
}

export default FacultyTabs;