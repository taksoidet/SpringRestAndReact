import React from 'react';
import ReactTable from "react-table";
import 'react-table/react-table.css'
import {SERVER_URL} from '../constants.js'
import { Button } from '@material-ui/core';
import { green } from '@material-ui/core/colors';


export default class Carlist extends React.Component{


    constructor(props){
        super(props);
        this.state={cars : []};
    }

    fetchCars(){
        const token=sessionStorage.getItem("jwt");
        fetch(SERVER_URL+'/api/cars', {
            headers : {'Authorization' : token}
        })
        .then((response)=>response.json())
        .then((responseData)=>{
            this.setState({cars : responseData._embedded.cars});
        }).catch(err => console.log(err));
    }

    componentDidMount(){
        this.fetchCars();
    }

    onDelClick=(link)=>{
        fetch(link, {method : 'DELETE', headers : {'Authorization' : sessionStorage.getItem("jwt")}})
        .then(res => this.fetchCars())
        .catch(err => console.log(err))
    }

    render(){
        const columns =[{Header : 'Brand', accessor : 'brand'},
                        {Header : 'Model', accessor : 'model'},
                        {Header : 'Color', accessor : 'color'},
                        {Header : 'Year', accessor : 'year'},
                        {Header : 'Price', accessor : 'price'},
                    {id : 'delbutton', sortable : false, filterable : false,
                width : 100, accessor : '_links.self.href' , 
            Cell : ({value})=>(<Button style={ {color : 'green'}} onClick={()=>{this.onDelClick(value)}}>Delete</Button>)}]
        
        return(<div className="App">
            <ReactTable data = {this.state.cars} columns={columns} filterable={true}/>
        </div>);
    }



}