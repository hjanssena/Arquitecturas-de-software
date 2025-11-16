import 'package:flutter/material.dart';

class LoginViewModel extends ChangeNotifier {
  bool _inRegistration = false;

  bool inRegistrationState() {
    return _inRegistration;
  }

  setRegistrationState(bool state) {
    _inRegistration = state;
    notifyListeners();
  }
}
