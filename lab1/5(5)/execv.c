#include <unistd.h>  
  
int main()  
{  
        char *argv[] = {"ls", "-l", "/etc", (char *)0};  
        execv("/bin/ls", argv);  
        return 0;  
}  
