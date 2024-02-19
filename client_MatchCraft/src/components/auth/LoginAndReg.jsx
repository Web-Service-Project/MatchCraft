import React, { useState } from 'react';
import RegForm from './RegForm';
import LoginForm from './LoginForm';
import { Container, Grid, Paper, Typography } from '@mui/material';
import { createTheme, ThemeProvider } from '@mui/material/styles';

// Define the theme without direct references to 'theme' variable inside its creation
const theme = createTheme({
  palette: {
    mode: 'dark', // Switch to 'light' mode if you prefer
  },
});

const LoginAndReg = () => {
  // Errors
  const [errors, setErrors] = useState({
    login: {
      error: "",
      email: "",
      password: ""
    },
    registration: {
      firstName: "",
      lastName: "",
      email: "",
      password: "",
      confirmPassword: ""
    }
  });

  return (
    <ThemeProvider theme={theme}>
      <Container component="main" maxWidth="lg" style={{ marginTop: theme.spacing(8) }}>
        <Typography component="h1" variant="h4" align="center" color="textPrimary" gutterBottom>
          Welcome to Our Platform
        </Typography>
        <Grid container spacing={3} justifyContent="center">
          <Grid item xs={12} sm={6}>
            <Paper elevation={6} style={{ padding: theme.spacing(2) }}>
              <RegForm errors={errors} setErrors={setErrors} />
            </Paper>
          </Grid>
          <Grid item xs={12} sm={6}>
            <Paper elevation={6} style={{ padding: theme.spacing(2) }}>
              <LoginForm errors={errors} setErrors={setErrors} />
            </Paper>
          </Grid>
        </Grid>
      </Container>
    </ThemeProvider>
  );
};

export default LoginAndReg;
