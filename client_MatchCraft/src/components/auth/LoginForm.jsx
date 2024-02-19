import React, { useState, useContext } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import UserContext from '../../context/UserContext';
import { TextField, Button, Card, CardContent, Typography, Container } from '@mui/material';
import { createTheme, ThemeProvider } from '@mui/material/styles';

// Create a dark theme instance
const darkTheme = createTheme({
  palette: {
    mode: 'dark',
  },
});

const LoginForm = (props) => {
    const {errors, setErrors, setComponent} = props
    const { saveLoggedInUser, setScoresAndPredictions } = useContext(UserContext)
    const navigate = useNavigate()

    // INPUT STORAGE
    const [loginInput, setLoginInput] = useState({
        email: "",
        password: ""
    });
    
    // ON CHANGE HANDLERS
    const handleLoginInputChange = e => {
        e.preventDefault();
    
        setLoginInput((prevInput) => ({
            ...prevInput, 
                [e.target.name]: e.target.value})
        )
    };
    
    // SUBMIT FUNCTIONS
    const handleLoginSubmit = (e) => {
        e.preventDefault();
        // error reset
        setErrors( prevErrors =>({
            ...prevErrors, 
                ["login"] : { 
                    error : ""
                }
        }));

        //FRONT END VALIDATE?
        if(!validate(loginInput)){
            return;
        }

        // API Call
        axios.post('http://localhost:8000/api/login', loginInput, {withCredentials: true})
            .then(res => {
                // save user to state and local memory
                saveLoggedInUser(res.data.user)
                // save scores and predictions to state
                setScoresAndPredictions(res.data.scoresAndPredictions)
                // redirect to dashboard
                navigate('/dashboard')
            })
            .catch(err => {
                // Add errors for display
                setErrors( prevErrors =>({
                    ...prevErrors, 
                        ["login"] : { 
                        ...prevErrors.login,
                            ["error"] : "Invalid Login Credentials"
                    }
            }));
        });
    };
    
    // Front end validation for login
    function validate(loginObject){
        const loginErrors = {}
        let isValid = true
        // check length
        if(loginObject['email'].length < 1){
            loginErrors.email = "Email is required"
            isValid = false
        }
        // check length
        if(loginObject['password'].length < 1){
            loginErrors.password = "Password is required"
            isValid = false
        }
        
        // Set the errors
        setErrors( prevErrors =>({
            ...prevErrors, 
                ["login"] : { 
                ...prevErrors.login,
                    ...loginErrors
                }
        }));
        return isValid
    }

    return (
        <ThemeProvider theme={darkTheme}>
          <Container component="main" maxWidth="xs">
            <Card sx={{ mt: 8 }}>
              <CardContent>
                <Typography component="h1" variant="h5" textAlign="center">
                  Login
                </Typography>
                <form onSubmit={handleLoginSubmit}>
                  <TextField
                    variant="outlined"
                    margin="normal"
                    required
                    fullWidth
                    id="email"
                    label="Email Address"
                    name="email"
                    autoComplete="email"
                    autoFocus
                    value={loginInput.email}
                    onChange={handleLoginInputChange}
                    error={!!errors.login?.email}
                    helperText={errors.login?.email}
                  />
                  <TextField
                    variant="outlined"
                    margin="normal"
                    required
                    fullWidth
                    name="password"
                    label="Password"
                    type="password"
                    id="password"
                    autoComplete="current-password"
                    value={loginInput.password}
                    onChange={handleLoginInputChange}
                    error={!!errors.login?.password}
                    helperText={errors.login?.password}
                  />
                  {errors.login?.error && (
                    <Typography color="error" textAlign="center">
                      {errors.login.error}
                    </Typography>
                  )}
                  <Button
                    type="submit"
                    fullWidth
                    variant="contained"
                    sx={{ mt: 3, mb: 2 }}
                  >
                    Login
                  </Button>
                  <Button
                    fullWidth
                    variant="outlined"
                    onClick={() => setComponent("")}
                  >
                    Cancel
                  </Button>
                </form>
              </CardContent>
            </Card>
          </Container>
        </ThemeProvider>
      );
    };
    
    export default LoginForm;