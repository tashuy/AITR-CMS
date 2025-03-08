import React from "react";
import { FcBusinessman } from "react-icons/fc";
import { PiStudentDuotone } from "react-icons/pi";
const Page3 = () => {
  return (
    <div className="bg-[#002147] h-screen w-full">
      <div className="flex flex-col items-center py-10">
        <h1 className="text-white text-center text-4xl font-medium">
          Categories
        </h1>
        <p className="w-[54vw] mt-5 text-lg text-slate-100 text-center">
          Discourse assurance estimable applauded to so. Him everything
          melancholy uncommonly but solicitude inhabiting projection off.
          Connection stimulated estimating excellence an to impression.
        </p>
        <div className="h-96 w-1/2 bg-red-50 rounded mt-20 flex gap-20 px-10 hover:shadow-2xl items-center justify-center">
          <div className="h-72 w-1/2 bg-red-500 rounded cursor-pointer shadow-custom">
            <h1 className="text-center py-5 text-2xl font-semibold">FACULTY</h1>
            <span className="flex items-center justify-center text-9xl">
              <FcBusinessman />
            </span>
          </div>
          <div className="h-72 w-1/2 bg-red-500 rounded cursor-pointer shadow-custom">
            <h1 className="text-center py-5 text-2xl font-semibold">
              STUDENTS
            </h1>
            <span className="flex items-center justify-center text-9xl">
              <PiStudentDuotone />
            </span>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Page3;
