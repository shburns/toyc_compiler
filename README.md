# toyc_compiler
The eclipse workspace for a compiler written in Java for the trivial (though computationally complete) 'ToyC' subset of the C language. It mostly works, in that the lexer and parser are both completely functional. The only place I had trouble was in the code generation and I intend to continue working on it. This was a wholly academic exercise and the ToyC language is neither terribly useful nor is the compiler for it robust enough to use on anything but small proof-of-concept programs. But it works for my purposes.

The bulk of the work is done in the compilers/toyc/src/parser directory, which is where the lexer and parser (recursive descent parser) do their work. It's where I documented all of my work and everything written therein is my own code. It's also where the tc.java driver file lives.

The compilers/toyc/src/abstractSyntax defines the abstract syntax of the language, is entirely my own code, but is not terribly well documented. The class definitions are so simplistic it seemed unnecessary to do so (or maybe I just got lazy).
