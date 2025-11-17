import 'package:dynadoc_front/views/Dashboard/TemplateListWidget.dart';
import 'package:flutter/material.dart';

class DashboardView extends StatelessWidget {
  const DashboardView({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Color.fromARGB(255, 85, 17, 136),
        title: Row(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            SizedBox(
              height: 90,
              child: IconButton(
                onPressed: () => {},
                icon: Image.asset("assets/images/logo-small-white.png"),
              ),
            ),
            TextButton(
              onPressed: () => {},
              child: Text(
                "Ver plantillas",
                style: TextStyle(
                  color: Color.fromARGB(255, 255, 255, 255),
                  fontSize: 20,
                ),
              ),
            ),
            TextButton(
              onPressed: () => {},
              child: Text(
                "Crear plantilla",
                style: TextStyle(
                  color: Color.fromARGB(255, 255, 255, 255),
                  fontSize: 20,
                ),
              ),
            ),
          ],
        ),
      ),
      body: Builder(
        builder: (BuildContext context) {
          return TemplateListWidget();
        },
      ),
    );
  }
}
