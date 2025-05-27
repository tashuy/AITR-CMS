
export const TabsElement = ( {setActiveTab, active, children, setSubTab } ) => {
    
    return <div>
        <button
        className={`px - 4 py-2 text-base font-semibold rounded ${active ? "bg-blue-500 text-white p-1" : "bg-gray-200"}`}
      onClick={() => { setActiveTab("faculty"); setSubTab(null); }}
      >
        {children}
      </button>
    </div>
}