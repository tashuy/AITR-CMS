
export const TabElement = ( {setActiveTab, active, tabName, setSubTab, activeTab } ) => {

    function onClickHandler (){
        setActiveTab(tabName)
        setSubTab(null);
        console.log(activeTab)
    }

    return <div>
        <button
            className={`px - 4 py-2 text-base font-semibold rounded ${active ? "bg-blue-500 text-white p-1" : "bg-gray-200"}`}
            onClick={ onClickHandler }
        >
            {tabName}
        </button>
    </div>
}