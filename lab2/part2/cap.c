#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/capability.h>
#include <errno.h>

void list_capability();

int main()
{
	cap_t caps = cap_init();
	cap_value_t capList[2] = {CAP_DAC_OVERRIDE, CAP_DAC_READ_SEARCH};
	unsigned num_caps = 2;
	cap_set_flag(caps, CAP_EFFECTIVE, num_caps, capList, CAP_SET);
	cap_set_flag(caps, CAP_INHERITABLE, num_caps, capList, CAP_SET);
	cap_set_flag(caps, CAP_PERMITTED, num_caps, capList, CAP_SET);

	if(cap_set_proc(caps))
	{
		perror("cap_set_proc");
	}

	list_capability();
	return 0;
}

void list_capability()
{
	cap_user_header_t cap_header = malloc(8);
	cap_user_data_t cap_data = malloc(12);

	cap_header->pid = getpid();
	cap_header->version = _LINUX_CAPABILITY_VERSION;

	if(capget(cap_header, cap_data) < 0)
	{
		perror("Failed capget");
		exit(1);
	}
	printf("Cap data permittted :0x%x, effective: 0x%x, inheritable:0x%x\n",
			cap_data->permitted, cap_data->effective, cap_data->inheritable);

}
