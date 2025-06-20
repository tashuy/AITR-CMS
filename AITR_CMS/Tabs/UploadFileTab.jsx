import React, { useState } from "react";

const UploadFileTab = () => {
  const [fileName, setFileName] = useState("");

  const handleFileChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setFileName(file.name);
    }
  };

  return (
    <div className="flex"> 
        <input
          id="file-upload"
          type="file"
          onChange={handleFileChange}
        />
        <p className="text-blue-600 font-semibold text-lg">
          {fileName ? fileName : "Upload file here"}
        </p>
    </div>
  );
};

export default UploadFileTab;
