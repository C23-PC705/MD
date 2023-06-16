package com.example.jimbro.ui.screens.login

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.jimbro.api.ApiConfig
import com.example.jimbro.api.request.*
import com.example.jimbro.api.responses.LoginResponse
import com.example.jimbro.api.responses.RegisterResponse
import com.example.jimbro.api.responses.UpdateBMI
import com.example.jimbro.api.responses.UpdatePassword
import com.example.jimbro.model.EditBmi
import com.example.jimbro.model.EditUser
import com.example.jimbro.model.UserPreference
import com.example.jimbro.ui.screens.EditBmi.EditBmiUIEvent
import com.example.jimbro.ui.screens.EditBmi.EditBmiUIState
import com.example.jimbro.ui.screens.EditPassword.EditPasswordUIEvent
import com.example.jimbro.ui.screens.EditPassword.EditPasswordUIState
import com.example.jimbro.ui.screens.EditProfile.EditProfileUIEvent
import com.example.jimbro.ui.screens.EditProfile.EditProfileUIState
import com.example.jimbro.ui.screens.Register.RegistrationUIState
import com.example.jimbro.ui.screens.Register.SignupUIEvent
import com.google.gson.Gson
import com.nativemobilebits.loginflow.data.login.LoginUIEvent
import com.nativemobilebits.loginflow.data.login.LoginUIState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response


sealed class UiState<out T : Any?> {
    object Loading : UiState<Nothing>()
    data class Success<out T : Any>(val data: T) : UiState<T>()
    data class Error(val errorMessage: String) : UiState<Nothing>()
}


class LoginViewModel(private val pref: UserPreference) : ViewModel() {


    private val TAG = LoginViewModel::class.simpleName

    var loginUIState = mutableStateOf(LoginUIState())

    var editUIState = mutableStateOf(EditProfileUIState())

    var editPasswordState = mutableStateOf(EditPasswordUIState())

    var registrationUIState = mutableStateOf(RegistrationUIState())

    var editBMIUIState = mutableStateOf(EditBmiUIState())

    var allValidationsPassed = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)

    var signUpInProgress = mutableStateOf(false)

    val API = ApiConfig.getApiService()

    private val _uiStateUser: MutableStateFlow<UiState<EditUser>> =
        MutableStateFlow(UiState.Loading)
    val uiStateUser: StateFlow<UiState<EditUser>>
        get() = _uiStateUser

    private val _uiStateBmi: MutableStateFlow<UiState<EditBmi>> = MutableStateFlow(UiState.Loading)
    val uiStateBmi: StateFlow<UiState<EditBmi>>
        get() = _uiStateBmi


    fun onEventLogin(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )
            }

            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
            }

            is LoginUIEvent.LoginButtonClicked -> {
                printState()
//                login(loginUIState.value.email, loginUIState.value.password)
                login(LoginRequest(loginUIState.value.email, loginUIState.value.password))
            }
        }
    }

    fun onEventRegister(event: SignupUIEvent) {
        when (event) {
            is SignupUIEvent.FirstNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    name = event.name
                )
                printState()
            }
            is SignupUIEvent.AgeChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    age = event.age
                )
                printState()
            }
            is SignupUIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
                printState()
            }
            is SignupUIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
                printState()
            }
            is SignupUIEvent.HeightChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    height = event.height
                )
                printState()
            }
            is SignupUIEvent.WeightChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    weight = event.weight
                )
                printState()
            }
            is SignupUIEvent.IntensityChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    intensity = event.intensity
                )
                printState()
            }
            is SignupUIEvent.genderChange -> {
                registrationUIState.value = registrationUIState.value.copy(
                    gender = event.gender
                )
                printState()
            }
            is SignupUIEvent.RegisterButtonClicked -> {
                signUp()
            }
        }
    }

    fun onEventEditProfile(event: EditProfileUIEvent) {
        when (event) {
            is EditProfileUIEvent.EmailChanged -> {
                editUIState.value = editUIState.value.copy(
                    email = event.email
                )
                printState()
            }
            is EditProfileUIEvent.PasswordChanged -> {
                editUIState.value = editUIState.value.copy(
                    password = event.password
                )
                printState()
            }
            is EditProfileUIEvent.AgeChanged -> {
                editUIState.value = editUIState.value.copy(
                    age = event.age
                )
                printState()
            }
            is EditProfileUIEvent.RegisterButtonClicked -> {
                editProfile(ProfileRequest(editUIState.value.email,
                    editUIState.value.password,
                    editUIState.value.age))
            }
        }
    }

    fun onEventChangePassword(event: EditPasswordUIEvent) {
        when (event) {
            is EditPasswordUIEvent.passwordLama -> {
                editPasswordState.value = editPasswordState.value.copy(
                    passwordLama = event.passwordLama
                )
                printState()
            }
            is EditPasswordUIEvent.passwordTerbaru -> {
                editPasswordState.value = editPasswordState.value.copy(
                    passwordTerbaru = event.passwordBaru
                )
                printState()
            }
            is EditPasswordUIEvent.passwordTerbaruUlang -> {
                editPasswordState.value = editPasswordState.value.copy(
                    passwordTerbaruUlang = event.passwordBaruUlang
                )
                printState()
            }
            is EditPasswordUIEvent.RegisterButtonClicked -> {
                if (editPasswordState.value.passwordTerbaru == editPasswordState.value.passwordTerbaruUlang) {
                    editPassword(updatePasswordRequest(editPasswordState.value.passwordLama,
                        editPasswordState.value.passwordTerbaru))
                } else {

                }

            }
        }
    }

    fun onEventBMI(event: EditBmiUIEvent) {
        when (event) {

            is EditBmiUIEvent.HeightChanged -> {
                editBMIUIState.value = editBMIUIState.value.copy(
                    height = event.height
                )
                printState()
            }
            is EditBmiUIEvent.WeightChanged -> {
                editBMIUIState.value = editBMIUIState.value.copy(
                    weight = event.weight
                )
                printState()
            }
            is EditBmiUIEvent.IntensityChanged -> {
                editBMIUIState.value = editBMIUIState.value.copy(
                    intensity = event.intensity
                )
                printState()
            }

            is EditBmiUIEvent.RegisterButtonClicked -> {
                editBMI(BmiRequest(editBMIUIState.value.height.toInt(),
                    editBMIUIState.value.weight.toInt(),
                    editBMIUIState.value.intensity))
            }

        }
    }

    private fun editBMI(profilBmi: BmiRequest) {

        Log.d(TAG, "Inside_edit_Bmi")
        printState()

        val client = API.updateBmi(profilBmi)
        client.enqueue(object : retrofit2.Callback<UpdateBMI> {
            override fun onResponse(
                call: Call<UpdateBMI>,
                response: Response<UpdateBMI>,
            ) {
//                _isLoading.value = false
                Log.e(TAG, response.toString())
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    Log.d(TAG, responseBody.toString())
                    if (responseBody != null) {

//                        _user.value = response.body()
                        viewModelScope.launch {
                            pref.updateBmi(profilBmi)
                        }

                        Log.d(TAG, "Response Succesful- ${response.message()}")
                    } else {
//                        _user.value = response.body()
                        Log.e(TAG, "Failed: Response Unsuccessful- ${response.message()}")
                    }
                }
                if (response.code() == 401) {
                    val gson = Gson()
                    loginInProgress.value = false
                    val message: LoginResponse = gson.fromJson(response.errorBody()!!.charStream(),
                        LoginResponse::class.java)
//                    _user.value = message
                }
            }

            override fun onFailure(call: Call<UpdateBMI>, t: Throwable) {
                loginInProgress.value = false
                Log.e(TAG, "Failed: Response Failure- ${t.message.toString()}")
            }
        })

    }

    private fun editPassword(updatePasswordRequest: updatePasswordRequest) {
        Log.d(TAG, "Inside_edit_password")
        printState()

        val client = API.updatePassword(updatePasswordRequest)
        client.enqueue(object : retrofit2.Callback<UpdatePassword> {
            override fun onResponse(
                call: Call<UpdatePassword>,
                response: Response<UpdatePassword>,
            ) {

                Log.e(TAG, response.toString())
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    Log.d(TAG, responseBody.toString())
                    if (responseBody != null) {


                        Log.d(TAG, "Response Succesful- ${response.message()}")
                    } else {
                        Log.e(TAG, "Failed: Response Unsuccessful- ${response.message()}")
                    }
                }
                if (response.code() == 401) {
                    val gson = Gson()

                    val message: UpdatePassword = gson.fromJson(response.errorBody()!!.charStream(),
                        UpdatePassword::class.java)

                }
            }

            override fun onFailure(call: Call<UpdatePassword>, t: Throwable) {
                loginInProgress.value = false
                Log.e(TAG, "Failed: Response Failure- ${t.message.toString()}")
            }
        })

    }

    private fun editProfile(profilData: ProfileRequest) {
        Log.d(TAG, "Inside_edit_profile")
        printState()

        val client = API.updateProfile(profilData)
        client.enqueue(object : retrofit2.Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>,
            ) {
//                _isLoading.value = false
                Log.e(TAG, response.toString())
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    Log.d(TAG, responseBody.toString())
                    if (responseBody != null) {
                        loginInProgress.value = true
//                        _user.value = response.body()
                        viewModelScope.launch {
                            pref.login(responseBody.user)
                        }
                        loginUIState.value = LoginUIState()
                        Log.d(TAG, "Response Succesful- ${response.message()}")
                    } else {
//                        _user.value = response.body()
                        Log.e(TAG, "Failed: Response Unsuccessful- ${response.message()}")
                    }
                }
                if (response.code() == 401) {
                    val gson = Gson()
                    loginInProgress.value = false
                    val message: LoginResponse = gson.fromJson(response.errorBody()!!.charStream(),
                        LoginResponse::class.java)
//                    _user.value = message
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loginInProgress.value = false
                Log.e(TAG, "Failed: Response Failure- ${t.message.toString()}")
            }
        })

    }

    private fun signUp() {
        Log.d(TAG, "Inside_signUp")
        printState()
//        createUser(
//            email = registrationUIState.value.email,
//            password = registrationUIState.value.password,
//            age = registrationUIState.value.age.toInt(),
//            height = registrationUIState.value.height.toInt(),
//            weight = registrationUIState.value.weight.toInt(),
//            intensity = registrationUIState.value.intensity,
//            gender = registrationUIState.value.gender
//
//        )
        createUser(RegisterRequest(
            email = registrationUIState.value.email,
            password = registrationUIState.value.password,
            age = registrationUIState.value.age,
            height = registrationUIState.value.height,
            weight = registrationUIState.value.weight,
            intensity = registrationUIState.value.intensity,
            gender = registrationUIState.value.gender
        )


        )
    }

    fun getedituser() {
        viewModelScope.launch {
            pref.getEditUser()
                .catch {
                    _uiStateUser.value = UiState.Error(it.message.toString())
                }.collect { data ->
                    Log.d("Test Data", data.toString())
                    _uiStateUser.value = UiState.Success(data)
                }
        }
    }

    fun geteditBmi() {
        viewModelScope.launch {
            pref.getEditBmi()
                .catch {
                    _uiStateBmi.value = UiState.Error(it.message.toString())
                }.collect { data ->
                    Log.d("Test Data", data.toString())
                    _uiStateBmi.value = UiState.Success(data)
                }
        }
    }

    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, loginUIState.value.toString())
    }

    private fun login(loginBody: LoginRequest) {

        val client = API
            .loginUser(loginBody)
        client.enqueue(object : retrofit2.Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>,
            ) {
//                _isLoading.value = false
                Log.e(TAG, response.toString())
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    Log.d(TAG, responseBody.toString())
                    if (responseBody != null) {
                        loginInProgress.value = true
//                        _user.value = response.body()
                        viewModelScope.launch {
                            pref.login(responseBody.user)
                        }
                        loginUIState.value = LoginUIState()
                        Log.d(TAG, "Response Succesful- ${response.message()}")
                    } else {
//                        _user.value = response.body()
                        Log.e(TAG, "Failed: Response Unsuccessful- ${response.message()}")
                    }
                }
                if (response.code() == 401) {
                    val gson = Gson()
                    loginInProgress.value = false
                    val message: LoginResponse = gson.fromJson(response.errorBody()!!.charStream(),
                        LoginResponse::class.java)
//                    _user.value = message
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loginInProgress.value = false
                Log.e(TAG, "Failed: Response Failure- ${t.message.toString()}")
            }
        })
    }

    private fun createUser(
        body: RegisterRequest,
    ) {
        val client = API
            .createUser(body)

        client.enqueue(object : retrofit2.Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>,
            ) {
//                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        signUpInProgress.value = true
//                        _user.value = response.body()
                        Log.d(TAG, "Response Succesful- ${response.message()}")
                    } else {
//                        _user.value = response.body()
                        Log.e(TAG, "Failed: Response Unsuccessful- ${response.message()}")
                    }
                }
                if (response.code() == 400) {
                    val gson = Gson()
                    signUpInProgress.value = false
                    val message: RegisterResponse =
                        gson.fromJson(response.errorBody()!!.charStream(),
                            RegisterResponse::class.java)
//                    _user.value = message
                }

            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                signUpInProgress.value = false
                Log.e(TAG, "Failed: Response Failure- ${t.message.toString()}")
            }

        })


    }
}