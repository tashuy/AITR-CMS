import React from "react";
import { FcBusinessman } from "react-icons/fc";
import { PiStudentDuotone } from "react-icons/pi";
import { BiSolidInstitution } from "react-icons/bi";
import { FcDepartment } from "react-icons/fc";
import { Link } from "react-router-dom";
const Page1 = () => {
  return (
    <div className="bg-[#002147] h-full w-full">
      <div className="flex flex-col items-center py-10">
        <h1 className="text-white text-center text-4xl font-medium">
          Categories
        </h1>
        <p className="w-[54vw] mt-5 text-lg text-slate-100 text-center">
          Discourse assurance estimable applauded to so. Him everything
          melancholy uncommonly but solicitude inhabiting projection off.
          Connection stimulated estimating excellence an to impression.
        </p>
        <div className="h-96 w-full ` rounded mt-20 flex gap-20 px-10 hover:shadow-2xl items-center justify-center">

          <Link to="/institute" className="h-60 w-1/2 bg-[#FFB606] rounded cursor-pointer shadow-custom flex flex-col justify-center">
            <h1 className="text-center pb-3 text-2xl font-semibold uppercase">institute</h1>
            <span className="flex items-center justify-center text-9xl">
            <BiSolidInstitution />
            </span>

          </Link>
          <Link to="/department" className="h-60 w-1/2 bg-[#FFB606] rounded cursor-pointer shadow-custom flex flex-col justify-center">
            <h1 className="text-center pb-3 text-2xl font-semibold uppercase">department</h1>
            <span className="flex items-center justify-center text-9xl">
              <FcDepartment />
            </span>
          </Link>

          <Link to="/faculty" className="h-60 w-1/2 bg-[#FFB606] rounded cursor-pointer shadow-custom flex flex-col justify-center">
            <h1 className="text-center pb-3 text-2xl font-semibold">FACULTY</h1>
            <span className="flex items-center justify-center text-9xl">
              <FcBusinessman />
            </span>
          </Link>

          <Link to="/students" className="h-60 w-1/2 bg-[#FFB606] rounded cursor-pointer shadow-custom flex flex-col justify-center">
            <h1 className="text-center pb-3 text-2xl font-semibold">
              STUDENTS
            </h1>
            <span className="flex items-center justify-center text-9xl">
              <PiStudentDuotone />
            </span>
          </Link>

        </div>
      </div>
    </div>
  );
};

export default Page1;