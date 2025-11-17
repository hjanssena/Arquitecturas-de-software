import 'package:flutter/material.dart';
import 'package:pdfx/pdfx.dart';

class TemplateListViewModel extends ChangeNotifier {
  PdfController? _pdfController;

  TemplateListViewModel() {
    setPdfController();
  }

  void setPdfController() {
    _pdfController = PdfController(
      document: PdfDocument.openAsset('pdf/pruebota.pdf'),
    );
  }

  PdfController? getPdfController() {
    return _pdfController;
  }

  List<Widget> getTemplateList() {
    List<Widget> widgets = [];
    for (int i = 0; i < 50; i++) {
      Card card = Card(
        child: TextButton(
          onPressed: () {},
          child: Padding(
            padding: EdgeInsets.all(15),
            child: Text('Pruebita $i'),
          ),
        ),
      );
      widgets.add(card as Widget);
    }
    return widgets;
  }

  List<Widget> getFields() {
    List<Widget> widgets = [];
    widgets.add(Text("Titulo de plantilla", style: TextStyle(fontSize: 25)));
    for (int i = 0; i < 12; i++) {
      Widget item = Padding(
        padding: const EdgeInsets.symmetric(horizontal: 20, vertical: 16),
        child: TextFormField(
          decoration: const InputDecoration(
            border: UnderlineInputBorder(),
            labelText: "Campo",
          ),
        ),
      );
      widgets.add(item);
    }
    return widgets;
  }
}
