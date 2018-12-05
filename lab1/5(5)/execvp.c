#include <unistd.h>  
  
int main()  
{  
        char *argv[] = {"ls", "-l", "/etc", (char *)0};  
        execvp("ls", argv);  
        return 0;  
}  
