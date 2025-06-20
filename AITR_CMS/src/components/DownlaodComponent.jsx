import React from 'react'


const DownloadComponent = ({url, filename}) => {

    const downloadCertificate = async (url, fileName) => {
    try {
      const response = await fetch(url, { mode: "cors" });
      const blob = await response.blob();

      const link = document.createElement("a");
      link.href = URL.createObjectURL(blob);
      link.download = `${fileName.replace(/\s+/g, "_")}_Certificate.pdf`;
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);

      URL.revokeObjectURL(link.href);
    } catch (err) {
      console.error("Error downloading PDF:", err);
      alert("Failed to download certificate.");
    }
  };
  return (
    <div>
        <button onClick={downloadCertificate}>
            Downlaod PDF
        </button>
    </div>
  )
}


export default DownloadComponent
