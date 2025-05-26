import React from "react";
import logo from "../assets/img/AITR_LOGO.png";
import { FaCopy } from "react-icons/fa";
import { FaUserShield } from "react-icons/fa";
import { IoCall } from "react-icons/io5";

const Navbar = () => {
  return (
    <div className="bg-[#002147] h-24 w-full flex items-center justify-between ">
      <div className="ml-44 ">
        <img className="h-24 max-w-xs object-contain" src={logo} alt="logo" />
      </div>
      <div className="mr-24">
        <ul>
          <li className="flex text-base ">
            <p className="px-6 font-medium  flex items-center gap-x-1">
              <FaCopy color="#FFB606" />
              TOTAL COURSES: <span className="font-semibold">50+</span>
            </p>
            <p className="px-6 font-medium flex items-center gap-x-1">
              <FaUserShield color="#FFB606" />
              INSTRUCTOR: <span className="font-semibold">20+</span>
            </p>
            <p className="px-6 font-medium flex items-center gap-x-1">
              <IoCall color="#FFB606" />
              HELP LINE: <span className="font-semibold">+9999999</span>
            </p>
          </li>
        </ul>
      </div>
    </div>
  );
};

export default Navbar;
