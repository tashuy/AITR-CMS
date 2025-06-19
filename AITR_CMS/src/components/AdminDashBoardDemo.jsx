import React, { useEffect, useRef } from "react"
import { useState } from "react"
import FacultyTabs from "../../Tabs/FacultyTab"
import StudentTabs from "../../Tabs/StudentTabs"
import Navbar from "./Navbar"

function AdminDashboardDemo () {
    const [tab, setTab] = useState(localStorage.getItem("tab"))
    const previousTab = useRef(tab);



    useEffect(() => {
        previousTab.current = tab
        localStorage.setItem("tab" , previousTab.current)
        
    }, [tab])


    return <>
    <Navbar />
    <div className="flex flex-col space-x-4 mb-4">
    <h2 className="flex text-2xl font-semibold mb-4 space-x-4">Admin Dashboard</h2>
    <div className="flex space-x-4 mb-4">
        <button className={`px-4 py-2 text-base font-semibold rounded ${tab =='faculty' && 'bg-blue-300' }`} onClick={() => setTab("faculty")} >
            Faculty
        </button>
        <button className={`px-4 py-2 text-base font-semibold rounded ${tab =='student' && 'bg-blue-300' }`} onClick={() => setTab("student")}>
            Student
        </button>
    </div>
    <div className="p-6" >
        { tab === "faculty" ? <FacultyTabs /> : <StudentTabs /> }
    </div>
    </div>
    </>
}


export default AdminDashboardDemo