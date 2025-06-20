
export const InputElement = ({
    placeholder,
    type,
    value,
    onChange,
    required
}) => {

    return <div>
        <input
              className="w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:outline-none"
              placeholder={placeholder}
              type={type}
              value={value}
              onChange={onChange}
              required
            />
    </div>
}