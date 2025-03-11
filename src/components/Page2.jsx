import React from "react";
import page2Img from "./img/page2.jpg";
import glimpse1 from "./img/page2-glimpse1.jpg";
import glimpse2 from "./img/page2-glimpse2.jpg";
import glimpse3 from "./img/page2-glimpse3.jpg";
import { GoGoal } from "react-icons/go";
import { HiOutlineBuildingOffice2 } from "react-icons/hi2";
const Page2 = () => {
  return (
    <div className="min-h-screen w-full place-items-center py-20 text-white">
      <div className="h-auto w-[70vw]  flex items-center gap-10">
        <div className="w-1/2  h-[80vh] ml-6">
          <img
            className="h-full w-full object-cover"
            src={page2Img}
            alt="page2Img"
          />
        </div>
        <div className="w-1/2  h-[80vh] mr-6">
          <h1 className="text-black text-[3vw] font-medium mt-16 leading-snug px-5">
            Acropolis Institute of Technology & Research, Indore
          </h1>
          <p className="text-slate-500 w-[32vw] mt-2 px-5">
            We, the community members from teachers and researchers to other
            staff members and students, at Acropolis aspire to establish a
            system of quality assurance, which would on a continuous basis
            evaluate and monitor the quality of education and training imparted
            at the institute.
          </p>
          <button className="text-slate-800 ml-5  uppercase mt-10 bg-[#FFB606] p-5 text-sm font-bold rounded-full">
            about aitr
          </button>
        </div>
      </div>
      <div className="h-[70vh] w-[70vw]  mt-10 flex">
        <div>
          <img className="h-[70vh] w-96 p-2" src={glimpse2} alt="glimpse2" />
        </div>

        <div className="flex  flex-col">
          <img className="h-1/2 p-2 w-96" src={glimpse3} alt="glimpse3" />
          <img className="h-1/2 w-96 p-2" src={glimpse1} alt="glimpse1" />
        </div>

        <div className="flex m-2  flex-col">
          <div className="h-1/2 mb-2 w-96 bg-[#002147] rounded">
            <h1 className="font-bold text-2xl pt-10 px-10 flex items-center gap-4">
              <HiOutlineBuildingOffice2 fontSize={32} />
              Our Vision
            </h1>
            <p className="px-[4vw] w-[26vw] py-2 text-slate-400 font-medium tracking-tight flex items-start indent-0 text-base">
              {" "}
              <span className="text-lg mr-5 text-white font-extrabold inline-block">
                &#8226;
              </span>
              To be an academic leader for the development of human potential so
              as to meet the global challenges.
            </p>
          </div>
          <div className="h-1/2 mt-2 w-96 bg-slate-100 text-black rounded">
            <h1 className="font-bold text-2xl px-10 my-5  flex items-center gap-4">
              <GoGoal fontSize={32} />
              Our Mission
            </h1>
            <p className="px-12 indent-0 flex text-base leading-5 -mt-1 mb-2 items-start ">
              <span className="text-lg mr-3 text-black font-extrabold inline-block">
                &#8226;
              </span>
              To create an intellectually stimulating learning environment.
            </p>

            <p className="px-12 indent-0 text-base leading-5 flex mb-2 items-start">
              <span className="text-lg mr-3 text-black font-extrabold inline-block">
                &#8226;
              </span>
              To impart value based, innovative, and research oriented
              education.
            </p>
            <p className="px-12 indent-0 text-base leading-5 mb-2  flex items-start">
              <span className="text-lg mr-3 text-black font-extrabold inline-block">
                &#8226;
              </span>
              To develop positive attitude with communication skills
            </p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Page2;
