import { API_BASE_URL } from "./config";


// Reusable Fetch Function
const fetchDataFromApi = async (url, method = "GET", requestedData = null) => {
    try {
        const options = {
            method,
            headers: {
                "Content-Type": "application/json",
            },
        };

        if (requestedData) {
            options.body = JSON.stringify(requestedData);
        }

        const response = await fetch(`${API_BASE_URL}${url}`, options);

        if (!response.ok) {
            throw new Error(`Failed with status ${response.status}`);
        }
        // console.log(response);
        const responseData = await response.json();
    
        console.log("API Response:", responseData);
        return responseData;
    } catch (error) {
        console.error(`Error: ${error.message}`);
        throw error;
    }
};

// Students API
export const fetchStudentData = async () => fetchDataFromApi('/student');
export const sendStudentData = async (data) => fetchDataFromApi('/student', "POST", data);
export const updateStudentData = async (data) => fetchDataFromApi('/student', "PUT", data);
export const deleteStudentData = async (data) => fetchDataFromApi('/student', "DELETE", data);

 //certificates
export const fetchCertificatestData = async () => fetchDataFromApi('/certificates');
export const sendCertificatesData = async (data) => fetchDataFromApi('/certificates', "POST", data);
export const updateCertificatesData = async (data) => fetchDataFromApi('/certificates', "PUT", data);
export const deleteCertificatesData = async (data) => fetchDataFromApi('/certificates', "DELETE", data);

//hackathons
export const fetchHackathonsData = async () => fetchDataFromApi('/hackathons');
export const sendHackathonsData = async (data) => fetchDataFromApi('/hackathons', "POST", data);
export const updateHackathonsData = async (data) => fetchDataFromApi('/hackathons', "PUT", data);
export const deleteHackathonsData = async (data) => fetchDataFromApi('/hackathons', "DELETE", data);


//internships
export const fetchInternshipsData = async () => fetchDataFromApi('/internships');
export const sendInternshipsData = async (data) => fetchDataFromApi('/internships', "POST", data);
export const updateInternshipsData = async (data) => fetchDataFromApi('/internships', "PUT", data);
export const deleteInternshipsData = async (data) => fetchDataFromApi('/internships', "DELETE", data);




//placement
export const fetchstudentplacementsData = async () => fetchDataFromApi('/student-placements');
export const sendstudentplacementsData = async (data) => fetchDataFromApi('/student-placements', "POST", data);
export const updatestudentplacementsData = async (data) => fetchDataFromApi('/student-placements', "PUT", data);
export const deletestudentplacementsData = async (data) => fetchDataFromApi('/student-placements', "DELETE", data);

//research paper
export const fetchResearchpapersData = async () => fetchDataFromApi('/research-papers');
export const sendResearchpapersData = async (data) => fetchDataFromApi('/research-papers', "POST", data);
export const updateResearchpapersData = async (data) => fetchDataFromApi('/research-papers', "PUT", data);
export const deleteResearchpapersData = async (data) => fetchDataFromApi('/research-papers', "DELETE", data);


//sports
export const fetchSportsData = async () => fetchDataFromApi('/sports');
export const sendSportsData = async (data) => fetchDataFromApi('/sports', "POST", data);
export const updateSportsData = async (data) => fetchDataFromApi('/sports', "PUT", data);
export const deleteSportsData = async (data) => fetchDataFromApi('/sports', "DELETE", data);












// Faculty API
export const fetchFacultyData = async () => fetchDataFromApi('/faculty');
export const sendFacultyData = async (data) => fetchDataFromApi('/faculty', "POST", data);
export const updateFacultyData = async (data) => fetchDataFromApi('/faculty', "PUT", data);
export const deleteFacultyData = async (data) => fetchDataFromApi('/faculty', "DELETE", data);


//faculty-awards
export const fetchFacultyawardsData = async () => fetchDataFromApi('/faculty-awards');
export const sendFacultyawardsData = async (data) => fetchDataFromApi('/faculty-awards', "POST", data);
export const updateFacultyawardsData = async (data) => fetchDataFromApi('/faculty-awards', "PUT", data);
export const deleteFacultyawardsData = async (data) => fetchDataFromApi('/faculty-awards', "DELETE", data);


//confrence
export const fetchFacultyconferencesData = async () => fetchDataFromApi('/faculty-conferences');
export const sendFacultyconferencesData = async (data) => fetchDataFromApi('/faculty-conferences', "POST", data);
export const updateFacultyconferencesData = async (data) => fetchDataFromApi('/faculty-conferences', "PUT", data);
export const deleteFacultyconferencesData = async (data) => fetchDataFromApi('/faculty-conferences', "DELETE", data);


//development program
export const fetchFacultyfdpsData = async () => fetchDataFromApi('/faculty-fdps');
export const sendFacultyfdpsData = async (data) => fetchDataFromApi('/faculty-fdps', "POST", data);
export const updateFacultyfdpsData = async (data) => fetchDataFromApi('/faculty-fdps', "PUT", data);
export const deleteFacultyfdpsData = async (data) => fetchDataFromApi('/faculty-fdps', "DELETE", data);


//faculty-patents
export const fetchFacultypatentsData = async () => fetchDataFromApi('/faculty-patents');
export const sendFacultypatentsData = async (data) => fetchDataFromApi('/faculty-patents', "POST", data);
export const updateFacultypatentsData = async (data) => fetchDataFromApi('/faculty-patents', "PUT", data);
export const deleteFacultypatentsData = async (data) => fetchDataFromApi('/faculty-patents', "DELETE", data);


//faculty-research-papers
export const fetchFacultyresearchpapersData = async () => fetchDataFromApi('/faculty-research-papers');
export const sendFacultyresearchpapersData = async (data) => fetchDataFromApi('/faculty-research-papers', "POST", data);
export const updateFacultyresearchpapersData = async (data) => fetchDataFromApi('/faculty-research-papers', "PUT", data);
export const deleteFacultyresearchpapersData = async (data) => fetchDataFromApi('/faculty-research-papers', "DELETE", data);





// MOU Event API
export const fetchMouEventData = async () => fetchDataFromApi('/mou-event');
export const sendMouEventData = async (data) => fetchDataFromApi('/mou-event', "POST", data);
export const updateMouEventData = async (data) => fetchDataFromApi('/mou-event', "PUT", data);
export const deleteMouEventData = async (data) => fetchDataFromApi('/mou-event', "DELETE", data);

// MOU Collaboration API
export const fetchMouCollabData = async () => fetchDataFromApi('/mou-collab');
export const sendMouCollabData = async (data) => fetchDataFromApi('/mou-collab', "POST", data);
export const updateMouCollabData = async (data) => fetchDataFromApi('/mou-collab', "PUT", data);
export const deleteMouCollabData = async (data) => fetchDataFromApi('/mou-collab', "DELETE", data);

// MOU Others API
export const fetchMouOthersData = async () => fetchDataFromApi('/mou-others');
export const sendMouOthersData = async (data) => fetchDataFromApi('/mou-others', "POST", data);
export const updateMouOthersData = async (data) => fetchDataFromApi('/mou-others', "PUT", data);
export const deleteMouOthersData = async (data) => fetchDataFromApi('/mou-others', "DELETE", data);

// Achievement API
export const fetchAchievementData = async () => fetchDataFromApi('/achievement');
export const sendAchievementData = async (data) => fetchDataFromApi('/achievement', "POST", data);
export const updateAchievementData = async (data) => fetchDataFromApi('/achievement', "PUT", data);
export const deleteAchievementData = async (data) => fetchDataFromApi('/achievement', "DELETE", data);




