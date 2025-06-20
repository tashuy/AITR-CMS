
export const LoginElement = () => {

    return <div className="flex justify-between items-center text-sm">
            <label className="flex items-center space-x-2">
              <input type="checkbox" className="w-4 h-4 text-blue-600" />
              <span className="text-gray-600">Remember Me</span>
            </label>
            <a href="#" className="text-blue-500 hover:underline">
              Lost your password?
            </a>
          </div>
}