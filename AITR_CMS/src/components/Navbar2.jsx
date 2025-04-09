import { Link, useNavigate, useLocation } from "react-router-dom";

const Navbar2 = () => {
  const navigate = useNavigate();
  const location = useLocation();

  const isActive = (path) => location.pathname === path;

  return (
    <div className="h-20 bg-slate-100 flex items-center justify-between text-black text-lg font-medium">
      <div className="ml-48 gap-10 flex items-center">
        <Link
          to="/"
          className={isActive("/") ? "text-blue-600 font-bold underline" : ""}
        >
          Home
        </Link>

        <Link
          to="/institute"
          className={isActive("/institute") ? "text-blue-600 font-bold underline" : ""}
        >
          Institute
        </Link>

        <Link
          to="/department"
          className={isActive("/department") ? "text-blue-600 font-bold underline" : ""}
        >
          Department
        </Link>

        <Link
          to="/faculty"
          className={isActive("/faculty") ? "text-blue-600 font-bold underline" : ""}
        >
          Faculty
        </Link>

        <Link
          to="/students"
          className={isActive("/students") ? "text-blue-600 font-bold underline" : ""}
        >
          Students
        </Link>
      </div>

      <div className="flex items-center mr-40 gap-4 text-base text-slate-800">
        <Link
          to="/admin"
          className={`border-2 border-slate-200 hover:bg-[#ffb406f0] rounded px-4 py-1 ${
            isActive("/admin") ? "bg-[#ffb406f0] font-bold" : ""
          }`}
        >
          Admin
        </Link>
      </div>
    </div>
  );
};

export default Navbar2;