import React from 'react';
import Carlist from './Carlist';
import '../css/App.css';    
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import {SERVER_URL} from '../constants.js'


export default class Login extends React.Component{

    constructor(props){
        super(props);
        this.state={username: '', password: '', isAuthenticated: false};
    }

    handleChange=(event) => {
        this.setState({[event.target.name] : event.target.value})
    };

    login = () => {
        const user={username: this.state.username, password: this.state.password};
        fetch(SERVER_URL + '/login',
        {
            method: 'POST',
            body: JSON.stringify(user)
        }).then(res => {
            const jwtToken=res.headers.get('Authorization');
            if(jwtToken !== null){
                sessionStorage.setItem("jwt", jwtToken);
                this.setState({isAuthenticated : true});
            }
        }).catch(err=>console.log(err))
    }

render(){
    if(this.state.isAuthenticated === true){
        return(<Carlist/>);
    }else{
    return(<div className="Login">
    <h1>Please Sign In</h1>
    <TextField name="username" placeholder="Username"
    onChange={this.handleChange}/> <br/>
    <TextField name="password" type="password" placeholder="Password"
    onChange={this.handleChange}/><br/>
    <Button variant="raised" color="primary" onClick={this.login}>
        Login
    </Button>
    </div>);
    }
    
   
    
}
}



