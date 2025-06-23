export const ButtonElement = ({
    children,onClick

}) => {
    return (
        <button onClick={onClick} className="px-4 py-2 text-base font-semibold bg-[#00062B] hover:bg-blue-900 text-white rounded">{children}</button>
    )
}