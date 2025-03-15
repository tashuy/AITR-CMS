import logo from './logo.svg';
import './App.css';

class App extends React.component{
  constructor(props){
   super(props);
   this.state={apiResponse:""}; 
  }
  callAPI(){
    fetch("https://localhost:9000/testAPI")
    .then(res=>res.text())
    .then(res=>this.setState({apiResponse:res}));
  }
  componentWillMount(){
    this.callAPI();
  }
}

render() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
      </header>
      <p>{this.state.apiResponse}</p>
    </div>
  );
}

export default App;
