import React, { useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import UserContext from '../../context/UserContext';
import axios from 'axios';
import { TextField, Button, Card, CardContent, Typography, Container } from '@mui/material';
import { createTheme, ThemeProvider } from '@mui/material/styles';

// Create a dark theme instance
const darkTheme = createTheme({
  palette: {
    mode: 'dark',
  },
});

const RegForm = (props) => {
    const {errors, setErrors, setComponent} = props
    const { saveLoggedInUser, setScoresAndPredictions } = useContext(UserContext)
    const navigate = useNavigate();

    // INPUT STORAGE
    const [regInput, setRegInput] = useState({
        firstName: "", 
        lastName: "", 
        email: "", 
        password: "", 
        confirmPassword: ""
    });

    // ONCHANGE HANDLER
    const handleRegistrationInputChange = e => {
        e.preventDefault();
    
        setRegInput((prevInput) => ({
            ...prevInput, 
                [e.target.name]: e.target.value})
        )
    };

    // Validation function
    function validate(regInput){
        const registrationErrors = {};
        let isValid = true;
    
        
        if (regInput.firstName.length < 3) {
            registrationErrors.firstName = 'First name must be at least 3 characters';
            isValid = false;
        }
    
        
        if (regInput.lastName.length < 3) {
            registrationErrors.lastName = 'Last name must be at least 3 characters';
            isValid = false;
        }
    
        
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(regInput.email)) {
            registrationErrors.email = 'Invalid email format';
            isValid = false;
        }
    
        
        if (regInput.password.length < 8) {
            registrationErrors.password = 'Password must be at least 8 characters';
            isValid = false;
        }
    
        // Set the errors
        setErrors((prevErrors) => ({
        ...prevErrors,
        ['registration']: {
            ...prevErrors['registration'],
            ...registrationErrors,
        },
        }));
    
        return isValid;
    };

    // ONSUBMIT FUNCTION
    const handleRegSubmit = (e) => {
        e.preventDefault();
        //! error reset
        setErrors(prevErrors =>( {
            ...prevErrors, 
                ["registration"] : {
                    firstName: "", 
                    lastName: "", 
                    email: "", 
                    password: "", 
                    confirmPassword: ""
            }
        }));
        //Validation for Register
        if(!validate(regInput)){
            return;
        }
    
        // make API call to register
        axios.post('http://localhost:8000/api/register', regInput, {withCredentials: true})
            .then(res => {
                saveLoggedInUser(res.data.user)
                setScoresAndPredictions(res.data.scoresAndPredictions)
                navigate('/dashboard') 
            })
            .catch(err => {
                setErrors( prevErrors => {
                    const formErrors = err.response.data.errors
                    const updatedErrors = {...prevErrors}
                    for(const field in formErrors){
                        const message = formErrors[field]["message"]
                        updatedErrors["registration"][field] = message
                }
                return updatedErrors;
            })
        });
    };
        


    return (
        <ThemeProvider theme={darkTheme}>
          <Container component="main" maxWidth="sm">
            <Card sx={{ mt: 8 }}>
              <CardContent>
                <Typography component="h1" variant="h5" textAlign="center">
                  Registration
                </Typography>
                <form onSubmit={handleRegSubmit}>
                  {/* Map over regInput keys to generate TextFields dynamically */}
                  {Object.keys(regInput).map((inputName) => (
                    <TextField
                      key={inputName}
                      variant="outlined"
                      margin="normal"
                      required
                      fullWidth
                      id={inputName}
                      label={inputName.charAt(0).toUpperCase() + inputName.slice(1).replace(/([A-Z])/g, ' $1').trim()} // Add space before capital letters
                      name={inputName}
                      value={regInput[inputName]}
                      onChange={handleRegistrationInputChange}
                      error={!!errors.registration?.[inputName]}
                      helperText={errors.registration?.[inputName]}
                      type={inputName.includes("password") ? "password" : "text"}
                      autoComplete={inputName}
                    />
                  ))}
                  <Button
                    type="submit"
                    fullWidth
                    variant="contained"
                    sx={{ mt: 3, mb: 2 }}
                  >
                    Register
                  </Button>
                  <Button
                    fullWidth
                    variant="outlined"
                    onClick={() => setComponent("")}
                    sx={{ mt: 1 }}
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
    
    export default RegForm;