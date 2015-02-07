package org.usfirst.frc.team3044.robot;

import org.usfirst.frc.team3044.DriverStation.SecondaryController;
import org.usfirst.frc.team3044.utils.Components;

public class Forklift {
	SecondaryController Joy = SecondaryController.getInstance();
	Components components = Components.getInstance();
	final int TOP = 1;
	final int MOVINGUP = 2;
	final int BOTTOM = 3;
	final int MOVINGDOWN = 4;
	final int STOPPEDMID = 5;
	final int OUT = 6;
	final int IN = 7;
	int ForkliftState = 3;
	int ClampState = 7;

	public void robotInit() {
		ForkliftState = BOTTOM;
		ClampState = OUT;
	}

	public void teleopInit() {
		components.forkliftLeft1.set(0);
		components.forkliftLeft2.set(0);
		components.forkliftRight1.set(0);
		components.forkliftRight2.set(0);

		components.forkliftClamp.set(true);
		components.forkliftClamp2.set(true);
	}

	public void autoInit() {
		components.forkliftLeft1.set(0);
		components.forkliftLeft2.set(0);
		components.forkliftRight1.set(0);
		components.forkliftRight2.set(0);

		components.forkliftClamp.set(true);
		components.forkliftClamp2.set(true);
	}

	public void disabled() {
		components.forkliftLeft1.set(0);
		components.forkliftLeft2.set(0);
		components.forkliftRight1.set(0);
		components.forkliftRight2.set(0);

		components.forkliftClamp.set(true);
		components.forkliftClamp2.set(true);
	}

	public void forkliftPeriodic() {
		switch (ForkliftState) {
		// SPACE
		case TOP:
			if (Joy.getLeftY() < 0) {
				if (!components.forkliftDown.get()) {
					ForkliftState = MOVINGDOWN;
					components.forkliftLeft1.set(-1);
					components.forkliftLeft2.set(-1);
					components.forkliftRight1.set(-1);
					components.forkliftRight2.set(-1);
				}
			}
			break;
		// SPACE
		case MOVINGDOWN:
			if (Joy.getRawButton(components.FORK_TOTE_BUTTON)) {
				if (components.forkliftTote.get()) {
					ForkliftState = STOPPEDMID;
					components.forkliftLeft1.set(0);
					components.forkliftLeft2.set(0);
					components.forkliftRight1.set(0);
					components.forkliftRight2.set(0);
				}
			}

			if (components.forkliftDown.get()) {
				ForkliftState = BOTTOM;
				components.forkliftLeft1.set(0);
				components.forkliftLeft2.set(0);
				components.forkliftRight1.set(0);
				components.forkliftRight2.set(0);
			}

			if (Joy.getLeftY() == 0) {
				ForkliftState = STOPPEDMID;
				components.forkliftLeft1.set(0);
				components.forkliftLeft2.set(0);
				components.forkliftRight1.set(0);
				components.forkliftRight2.set(0);
			}

			if (Joy.getLeftY() > 0) {
				if (!components.forkliftUp.get()) {
					ForkliftState = MOVINGUP;
					components.forkliftLeft1.set(1);
					components.forkliftLeft2.set(1);
					components.forkliftRight1.set(1);
					components.forkliftRight2.set(1);
				}
			}
			break;
		// SPACE
		case BOTTOM:
			if (Joy.getLeftY() > 0) {
				if (!components.forkliftUp.get()) {
					ForkliftState = MOVINGUP;
					components.forkliftLeft1.set(1);
					components.forkliftLeft2.set(1);
					components.forkliftRight1.set(1);
					components.forkliftRight2.set(1);
				}
			}
			break;
		// SPACE
		case MOVINGUP:
			if (Joy.getRawButton(components.FORK_TOTE_BUTTON)) {
				if (components.forkliftTote.get()) {
					ForkliftState = STOPPEDMID;
					components.forkliftLeft1.set(0);
					components.forkliftLeft2.set(0);
					components.forkliftRight1.set(0);
					components.forkliftRight2.set(0);
				}
			}

			if (components.forkliftUp.get()) {
				ForkliftState = TOP;
				components.forkliftLeft1.set(0);
				components.forkliftLeft2.set(0);
				components.forkliftRight1.set(0);
				components.forkliftRight2.set(0);
			}

			if (Joy.getLeftY() < 0) {
				if (!components.forkliftDown.get()) {
					ForkliftState = MOVINGDOWN;
					components.forkliftLeft1.set(-1);
					components.forkliftLeft2.set(-1);
					components.forkliftRight1.set(-1);
					components.forkliftRight2.set(-1);
				}
			}

			if (Joy.getLeftY() == 0) {
				ForkliftState = STOPPEDMID;
				components.forkliftLeft1.set(0);
				components.forkliftLeft2.set(0);
				components.forkliftRight1.set(0);
				components.forkliftRight2.set(0);
			}
			break;
		// SPACE
		case STOPPEDMID:
			if (Joy.getLeftY() > 0) {
				if (!components.forkliftUp.get()) {
					ForkliftState = MOVINGUP;
					components.forkliftLeft1.set(1);
					components.forkliftLeft2.set(1);
					components.forkliftRight1.set(1);
					components.forkliftRight2.set(1);
				}
			}

			if (Joy.getLeftY() < 0) {
				if (!components.forkliftDown.get()) {
					ForkliftState = MOVINGDOWN;
					components.forkliftLeft1.set(-1);
					components.forkliftLeft2.set(-1);
					components.forkliftRight1.set(-1);
					components.forkliftRight2.set(-1);
				}
			}

			if (Joy.getLeftY() < 0) {
				if (!Joy.getRawButton(components.FORK_TOTE_BUTTON)) {
					if (!components.forkliftTote.get()) {
						ForkliftState = MOVINGDOWN;
						components.forkliftLeft1.set(-1);
						components.forkliftLeft2.set(-1);
						components.forkliftRight1.set(-1);
						components.forkliftRight2.set(-1);
					}
				}
			}

			if (Joy.getLeftY() > 0) {
				if (!Joy.getRawButton(components.FORK_TOTE_BUTTON)) {
					if (!components.forkliftTote.get()) {
						ForkliftState = MOVINGUP;
						components.forkliftLeft1.set(1);
						components.forkliftLeft2.set(1);
						components.forkliftRight1.set(1);
						components.forkliftRight2.set(1);
					}
				}
			}
			break;
		}
		// SPACE
		switch (ClampState) {
		case OUT:
			if (Joy.getRawButton(components.FORK_OUT_BUTTON)) {
				components.forkliftClamp.set(true);
				components.forkliftClamp2.set(true);
			}
			break;
		case IN:
			if (Joy.getRawButton(components.FORK_IN_BUTTON)) {
				components.forkliftClamp.set(false);
				components.forkliftClamp2.set(false);
			}
			break;
		}
	}
}