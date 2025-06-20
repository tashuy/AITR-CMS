import React from 'react'

function FileUploader() {
  return (
    <div>
        <form action='http://localhost:8080/upload' method='POST' id='uploadForm' encType='multipart/form-data'>
        <input type='file' name='sampleFile' />
        <input type='submit' value="uplaod" />
        </form>
    </div>
  )
}

export default FileUploader