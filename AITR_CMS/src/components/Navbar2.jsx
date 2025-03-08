import { Link, useNavigate } from "react-router-dom";

const Navbar2 = () => {
  const navigate = useNavigate();

  return (
    <div className="h-20 bg-slate-100 flex items-center justify-between text-black text-lg font-medium">
      <div className="ml-48 gap-10 flex items-center">
        <Link to="/">Home</Link>
       
        <Link to="/faculty">Faculty</Link>
        <Link to="/students">Students</Link>
      </div>
      <div className="flex items-center mr-40 gap-4 text-base text-slate-800">
        
        <Link to="/admin"
          className="border-2 border-slate-200 hover:bg-[#ffb406f0] rounded px-4 py-1"
        >
          Admin
        </Link>
      </div>
    </div>
  );
};

export default Navbar2;
