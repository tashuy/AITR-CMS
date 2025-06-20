import { useEffect, useRef, useState } from "react";
import DataTable, { Direction, Media } from 'react-data-table-component';




function StudentTabs(){
    
    const [subTab, setSubTab ] = useState(localStorage.getItem('currentTab'))
    const [entry , setEntry] = useState([]);
    const [data, setdata] = useState([])

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

    const studentTabs = ["certificate", "hackathon", "placement", "internship", "researchpaper", "sports"];


    const getFormFields = () => {

        if(subTab == "certificate"){
            return ["Id", "Student Name", "Enrollment Number", "Certificate Name", "Certificate Type", "Issued By", "Issue Date", "Validity Period", "Grade Or Score", "Certificate Description", "Mode Of Training", "Related Course Or Program", "Certificate Status", "Verified", "Certificate Link", "Actions"];
        }
        else if(subTab === "hackathon"){
            return ["Id", "Student Name", "Enrollment Number", "Event Name", "Date", "Team Name", "Team Size", "Mentor Name", "Hackathon Type", "Organizing Body", "Venue", "Problem Statement", "Technology Used", "Prize Money", "Sponsoring Company", "Position", "Project Github Link", "Project Description", "Certificate Status", "Certificate Link", "Actions"];
        }
        else if (subTab === "placement"){
            return ["Id", "Student Name", "Company Name", "Job Role", "Branch", "Placement Type", "Package", "Joining Date", "Offer Letter Pdf", "Company Location", "Interview Mode", "Actions"];
            
        }
        else if(subTab == "internship"){
            return ["Id", "Student Name", "Enrollment Number", "Company Name", "Role", "Internship Type", "Stipend", "Duration", "Department", "Mentor Name", "Mentor Email", "Technologies Used", "Project Name", "Project Description", "Skills Gained", "Company Location", "Internship Status", "Start Date", "End Date", "Offer Letter Link", "Experience Letter Link", "Certificate Link", "Actions"];
          
        }
        else if(subTab == "researchpaper" ){
            return ["Id", "Student Name", "Title", "Publication Date", "Journal Name", "Co-Authors", "Actions"];
          
        }
        else if(subTab == "sports"){
          return ["Id", "Student Name", "Sport Name", "Achievement", "Event Date", "Event Name", "Event Level", "Event Location", "Position", "Certificate", "Coach Name", "Actions"];

        }
        else{
            return [];
        }
        
    }

    const getTableHeaders = () => {
        
      // todo : add export csv from the library

      if(subTab == "faculty"){
          return ["Name", "email", "department", "mobile_no", "years_Of_Experience", "designation"];
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
        cell: () => ( <DownloadComponent />),
      } : {
      name: header.split("_").join(" "),
      selector: row => row[header.toLowerCase()],    
      } )
  ) )





    // const data = [
  	//   {
    //     id: 1,
    //     title: '1988',
    //     faculty_name: 'Ajay',
    //     publication_date: 23,
    //     journal_name:"ajay",
    //     co_authors:"ajay",
    //     actions:"delete",
    //     url: "downlaod"
	  //   }
    // ]


    useEffect(() => {
        previousSubTab.current = subTab
        localStorage.setItem('currentTab' , previousSubTab.current)
        
    }, [subTab])



    return <>
    <div className="flex space-x-4 mb-4">
      {studentTabs.map((tab) => (
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
    <form className="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4" >
    {getFormFields().map((field) => (
      ( field == "certificate_Pdf" ? 
      <input
        key={field}
        type="file"
        value={(e) => e.target.value }
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
 
    <button type="submit" className="px-4 py-2 text-base font-semibold bg-[#00062B] text-white rounded">
    {"Add this "}
    </button>
    </form>
    <DataTable columns={column} data={data} customStyles={customStyles}  />

    
    </>
}

export default StudentTabs;