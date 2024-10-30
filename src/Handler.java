import java.util.Vector;import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Vector;

public class Handler {
    private Vector<String> _options = new Vector<String>();
    public IMethod _method;
    public IOperationRepo _operation;
    private Vector<String> _commandPartioned = new Vector<String>();

    Handler(IMethod method, IOperationRepo operation) {
        _method = method;
        _operation = operation;
    }

    public void execute(String input) throws Exception {
        boolean exceptionRaised = false;

        for (String string : input.split("\\s+")) {
            //this way of getting the options will fail in the piping with many options on a bigger scale all options in deffirent methods will gather
            if (string.charAt(0) == '-')
                _options.add(string);
            else
                _commandPartioned.add(string);
        }

        //handling indetned words
        String indentedWord = "";
        boolean foundIndented = false;

        for (int i = 0; _commandPartioned.size() > i; ++i) {
            String word = _commandPartioned.get(i);
            for (int j = 0; word.length() > j; ++j) {
                if (foundIndented && word.charAt(j) == '"') {
                    foundIndented = false;
                    break;
                }

                if (foundIndented)
                    indentedWord += word.charAt(j);

                if (word.charAt(j) == '"' && !foundIndented)
                    foundIndented = true;
            }
            if (foundIndented) {
                _commandPartioned.remove(i);
                i--;
                indentedWord += " ";
            }

            if (!foundIndented && !indentedWord.equals("")) {
                _commandPartioned.add(i, indentedWord);
                _commandPartioned.remove(i + 1);
                break;
            }
        }
        for (int i = 0; i < _commandPartioned.size() && !exceptionRaised; ++i) {
            switch (_commandPartioned.get(i)) {
                case "mkdir":
                    _method.makeDiractory(_commandPartioned.get(i + 1));
                    _commandPartioned.remove(i);
                    _commandPartioned.remove(i);
                    break;
                case "rmdir":
                    _method.removeDiractory(_commandPartioned.get(i + 1));
                    _commandPartioned.remove(i);
                    _commandPartioned.remove(i);
                    break;
                case "cat":
                    _commandPartioned.set(i, _method.concatenateFiles(_commandPartioned.get(i + 1)));
                    _commandPartioned.remove(i + 1);
                    break;
                default:
                    throw new Exception("could not find method");

            }

            if (_commandPartioned.size() > 1)
                System.out.println("no such method with given parameters was found check your input");

            else if (_commandPartioned.size() == 1)
                System.out.println(_commandPartioned.get(0));
        }
    }
}

